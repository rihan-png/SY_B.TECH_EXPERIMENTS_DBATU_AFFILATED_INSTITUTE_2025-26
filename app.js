/* ==========================================================
   DATA ANALYST PORTFOLIO DASHBOARD — app.js
   Chart.js 4 | PapaParse | Vanilla JS
   ========================================================== */

'use strict';

const REGION_COLORS = {
  North: { bg: 'rgba(36,81,166,0.75)',  border: '#2451a6' },
  South: { bg: 'rgba(16,185,129,0.75)', border: '#10b981' },
  East:  { bg: 'rgba(245,158,11,0.75)', border: '#f59e0b' },
  West:  { bg: 'rgba(124,58,237,0.75)', border: '#7c3aed' },
};
const DEFAULT_COLOR = { bg: 'rgba(100,116,139,0.65)', border: '#64748b' };

let rawData = [];
let currentData = [];
let charts = {};

/* ── Helpers ──────────────────────────────────────────── */
function fmt(n)  { return Number(n).toLocaleString('en-IN'); }
function fmtK(n) { return n >= 1000 ? (n / 1000).toFixed(1) + 'K' : n; }

function regionColor(r, type) {
  return (REGION_COLORS[r] || DEFAULT_COLOR)[type];
}

function destroyChart(id) {
  if (charts[id]) { charts[id].destroy(); delete charts[id]; }
}

/* ── CSV Loading ──────────────────────────────────────── */
function loadData() {
  document.getElementById('loading-overlay').style.display = 'flex';

  Papa.parse('cleaned_data.csv', {
    download: true,
    header: true,
    skipEmptyLines: true,
    complete({ data }) {
      rawData = data.map(row => ({
        date:        row.date ? row.date.trim() : '',
        region:      row.region ? row.region.trim() : '',
        sales:       parseFloat(row.sales)       || 0,
        conversions: parseFloat(row.conversions) || 0,
        visits:      parseFloat(row.visits)      || 0,
      })).filter(r => r.date && r.region);

      initFilters();
      applyFilters();
      document.getElementById('loading-overlay').style.display = 'none';
    },
    error(err) {
      console.error('CSV load error:', err);
      document.getElementById('loading-overlay').style.display = 'none';
      alert('Could not load cleaned_data.csv. Please check the file path.');
    }
  });
}

/* ── Filters ──────────────────────────────────────────── */
function initFilters() {
  const regions = [...new Set(rawData.map(d => d.region))].sort();
  const sel = document.getElementById('region-filter');
  sel.innerHTML = '<option value="All">All Regions</option>';
  regions.forEach(r => {
    const opt = document.createElement('option');
    opt.value = r; opt.textContent = r;
    sel.appendChild(opt);
  });

  const dates = rawData.map(d => d.date).sort();
  if (dates.length) {
    document.getElementById('start-date').value = dates[0];
    document.getElementById('end-date').value   = dates[dates.length - 1];
  }
}

function applyFilters() {
  const region = document.getElementById('region-filter').value;
  const start  = document.getElementById('start-date').value;
  const end    = document.getElementById('end-date').value;

  currentData = rawData.filter(d => {
    if (region !== 'All' && d.region !== region) return false;
    if (start && d.date < start) return false;
    if (end   && d.date > end)   return false;
    return true;
  });

  updateKPIs();
  drawSalesTrend();
  drawSalesBar();
  drawDoughnut();
  drawScatter();
  drawTable();
}

document.getElementById('apply-filters').addEventListener('click', applyFilters);
document.getElementById('reset-filters').addEventListener('click', () => {
  document.getElementById('region-filter').value = 'All';
  initFilters();   // re-sets date range
  applyFilters();
});

/* ── KPI Cards ────────────────────────────────────────── */
function updateKPIs() {
  const totalSales  = currentData.reduce((s, d) => s + d.sales, 0);
  const totalConv   = currentData.reduce((s, d) => s + d.conversions, 0);
  const totalVisits = currentData.reduce((s, d) => s + d.visits, 0);
  const convRate    = totalVisits ? (totalConv / totalVisits * 100).toFixed(2) : '0.00';
  const days        = new Set(currentData.map(d => d.date)).size || 1;
  const avgSales    = Math.round(totalSales / days);

  document.getElementById('total-sales-val').textContent = '₹ ' + fmt(totalSales);
  document.getElementById('conv-rate-val').textContent   = convRate + '%';
  document.getElementById('total-visits-val').textContent = fmt(totalVisits);
  document.getElementById('avg-sales-val').textContent   = '₹ ' + fmt(avgSales);

  document.getElementById('sales-trend').textContent = fmt(totalConv) + ' conversions';
  document.getElementById('visits-sub').textContent  = fmt(totalConv) + ' converted';
  document.getElementById('avg-sub').textContent     = days + ' days in range';
}

/* ── Chart 1: Sales Trend (line) ──────────────────────── */
function drawSalesTrend() {
  destroyChart('trend');
  const ctx = document.getElementById('trend-line').getContext('2d');

  const dateMap = {};
  currentData.forEach(d => {
    dateMap[d.date] = (dateMap[d.date] || 0) + d.sales;
  });
  const labels = Object.keys(dateMap).sort();
  const values = labels.map(l => dateMap[l]);

  charts.trend = new Chart(ctx, {
    type: 'line',
    data: {
      labels,
      datasets: [{
        label: 'Total Sales (₹)',
        data: values,
        borderColor: '#2451a6',
        backgroundColor: 'rgba(36,81,166,0.08)',
        fill: true,
        tension: 0.4,
        pointRadius: 4,
        pointHoverRadius: 7,
        pointBackgroundColor: '#2451a6',
        borderWidth: 2.5,
      }]
    },
    options: {
      responsive: true,
      maintainAspectRatio: false,
      plugins: {
        legend: { display: false },
        tooltip: {
          callbacks: {
            label: ctx => '₹ ' + fmt(ctx.raw)
          }
        }
      },
      scales: {
        x: { grid: { display: false }, ticks: { maxRotation: 45, font: { size: 11 } } },
        y: {
          grid: { color: 'rgba(0,0,0,.05)' },
          ticks: { callback: v => '₹' + fmtK(v), font: { size: 11 } }
        }
      }
    }
  });
}

/* ── Chart 2: Sales by Region (bar) ──────────────────── */
function drawSalesBar() {
  destroyChart('bar');
  const ctx = document.getElementById('sales-bar').getContext('2d');

  const regionMap = {};
  currentData.forEach(d => {
    regionMap[d.region] = (regionMap[d.region] || 0) + d.sales;
  });
  const labels = Object.keys(regionMap).sort();

  charts.bar = new Chart(ctx, {
    type: 'bar',
    data: {
      labels,
      datasets: [{
        label: 'Sales (₹)',
        data: labels.map(l => regionMap[l]),
        backgroundColor: labels.map(l => regionColor(l, 'bg')),
        borderColor:     labels.map(l => regionColor(l, 'border')),
        borderWidth: 2,
        borderRadius: 6,
      }]
    },
    options: {
      responsive: true,
      maintainAspectRatio: false,
      plugins: {
        legend: { display: false },
        tooltip: { callbacks: { label: ctx => '₹ ' + fmt(ctx.raw) } }
      },
      scales: {
        x: { grid: { display: false } },
        y: {
          grid: { color: 'rgba(0,0,0,.05)' },
          ticks: { callback: v => '₹' + fmtK(v) }
        }
      }
    }
  });
}

/* ── Chart 3: Doughnut — Region Share ────────────────── */
function drawDoughnut() {
  destroyChart('doughnut');
  const ctx = document.getElementById('region-doughnut').getContext('2d');

  const regionMap = {};
  currentData.forEach(d => {
    regionMap[d.region] = (regionMap[d.region] || 0) + d.sales;
  });
  const labels = Object.keys(regionMap).sort();

  charts.doughnut = new Chart(ctx, {
    type: 'doughnut',
    data: {
      labels,
      datasets: [{
        data: labels.map(l => regionMap[l]),
        backgroundColor: labels.map(l => regionColor(l, 'bg')),
        borderColor:     labels.map(l => regionColor(l, 'border')),
        borderWidth: 2,
        hoverOffset: 8,
      }]
    },
    options: {
      responsive: true,
      maintainAspectRatio: false,
      plugins: {
        legend: { position: 'bottom', labels: { padding: 16, font: { size: 12 } } },
        tooltip: {
          callbacks: {
            label: ctx => {
              const total = ctx.dataset.data.reduce((a, b) => a + b, 0);
              const pct   = total ? (ctx.raw / total * 100).toFixed(1) : 0;
              return ` ${ctx.label}: ₹${fmt(ctx.raw)} (${pct}%)`;
            }
          }
        }
      }
    }
  });
}

/* ── Chart 4: Scatter — Conversions vs Visits ─────────── */
function drawScatter() {
  destroyChart('scatter');
  const ctx = document.getElementById('scatter-chart').getContext('2d');

  const regions = [...new Set(currentData.map(d => d.region))].sort();
  const datasets = regions.map(r => ({
    label: r,
    data: currentData
      .filter(d => d.region === r)
      .map(d => ({ x: d.visits, y: d.conversions })),
    backgroundColor: regionColor(r, 'bg'),
    borderColor:     regionColor(r, 'border'),
    pointRadius: 6,
    pointHoverRadius: 9,
  }));

  charts.scatter = new Chart(ctx, {
    type: 'scatter',
    data: { datasets },
    options: {
      responsive: true,
      maintainAspectRatio: false,
      plugins: {
        legend: { position: 'bottom', labels: { padding: 14, font: { size: 12 } } },
        tooltip: {
          callbacks: {
            label: ctx => ` ${ctx.dataset.label} — Visits: ${fmt(ctx.parsed.x)}, Conv: ${fmt(ctx.parsed.y)}`
          }
        }
      },
      scales: {
        x: {
          title: { display: true, text: 'Visits', font: { weight: '600' } },
          grid:  { color: 'rgba(0,0,0,.05)' },
          ticks: { callback: v => fmtK(v) }
        },
        y: {
          title: { display: true, text: 'Conversions', font: { weight: '600' } },
          grid:  { color: 'rgba(0,0,0,.05)' },
          ticks: { callback: v => fmtK(v) }
        }
      }
    }
  });
}

/* ── Data Table ───────────────────────────────────────── */
function drawTable() {
  const tbody = document.getElementById('table-body');
  tbody.innerHTML = '';

  const sorted = [...currentData].sort((a, b) => a.date.localeCompare(b.date));
  sorted.forEach(row => {
    const rate = row.visits ? (row.conversions / row.visits * 100).toFixed(2) + '%' : '—';
    const tr = document.createElement('tr');
    tr.innerHTML = `
      <td>${row.date}</td>
      <td><span class="badge-region region-${row.region}">${row.region}</span></td>
      <td>₹ ${fmt(row.sales)}</td>
      <td>${fmt(row.conversions)}</td>
      <td>${fmt(row.visits)}</td>
      <td>${rate}</td>`;
    tbody.appendChild(tr);
  });

  document.getElementById('table-count').textContent =
    `Showing ${sorted.length} record${sorted.length !== 1 ? 's' : ''}`;
}

/* ── CSV Export ───────────────────────────────────────── */
document.getElementById('export-csv').addEventListener('click', () => {
  const header = 'date,region,sales,conversions,visits\n';
  const rows   = currentData.map(d =>
    `${d.date},${d.region},${d.sales},${d.conversions},${d.visits}`
  ).join('\n');
  const blob = new Blob([header + rows], { type: 'text/csv' });
  const url  = URL.createObjectURL(blob);
  const a    = document.createElement('a');
  a.href = url;
  a.download = 'filtered_data.csv';
  a.click();
  URL.revokeObjectURL(url);
});

/* ── Bootstrap ────────────────────────────────────────── */
window.addEventListener('DOMContentLoaded', loadData);
