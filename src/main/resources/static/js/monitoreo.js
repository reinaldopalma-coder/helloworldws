// Inicializar mapa centrado con zoom cercano (18) por defecto
const map = L.map('map').setView([-38.749181, -72.629824667], 18);
L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    maxZoom: 19, attribution: '© OpenStreetMap'
}).addTo(map);

let markersGroup = L.layerGroup().addTo(map);
let todosLosLogs = [];
let fechasUnicasOrdenadas = [];

function inicializarTelemetria() {
    document.getElementById('txtStatus').innerText = "Conectando con la API...";
    
    fetch('/api/logGanado')
        .then(response => response.json())
        .then(data => {
            if (data.length === 0) {
                document.getElementById('txtStatus').innerText = "Sin datos en MySQL.";
                return;
            }

            todosLosLogs = data.sort((a, b) => new Date(a.fechaCreacion) - new Date(b.fechaCreacion));
            fechasUnicasOrdenadas = todosLosLogs.map(log => log.fechaCreacion);

            const slider = document.getElementById('timeSlider');
            slider.max = fechasUnicasOrdenadas.length - 1;
            slider.value = fechasUnicasOrdenadas.length - 1;

            document.getElementById('startTimeLabel').innerText = new Date(fechasUnicasOrdenadas[0]).toLocaleString();
            document.getElementById('endTimeLabel').innerText = new Date(fechasUnicasOrdenadas[fechasUnicasOrdenadas.length - 1]).toLocaleString();
            
            document.getElementById('timelineUi').style.visibility = 'visible';
            renderizarMapaEnInstante(fechasUnicasOrdenadas.length - 1);
        })
        .catch(error => {
            console.error("Error:", error);
            document.getElementById('txtStatus').innerText = "Error al leer servidor.";
        });
}

function actualizarMapaPorSlider(index) {
    renderizarMapaEnInstante(parseInt(index));
}

function renderizarMapaEnInstante(indexSlider) {
    markersGroup.clearLayers();

    const fechaCorteStr = fechasUnicasOrdenadas[indexSlider];
    const fechaCorte = new Date(fechaCorteStr);

    const esUltimo = (indexSlider === fechasUnicasOrdenadas.length - 1);
    document.getElementById('currentTimeLabel').innerText = esUltimo 
        ? `Actual: ${fechaCorte.toLocaleString()}`
        : `Histórico: ${fechaCorte.toLocaleString()}`;

    const logsHastaElMomento = todosLosLogs.filter(log => new Date(log.fechaCreacion) <= fechaCorte);
    const ultimasPosicionesDispositivos = {};

    logsHastaElMomento.forEach(log => {
        ultimasPosicionesDispositivos[log.dispositivoId] = log;
    });

    const listaDispositivos = Object.values(ultimasPosicionesDispositivos);
    
    if (listaDispositivos.length > 0) {
        map.setView([listaDispositivos[0].latitud, listaDispositivos[0].longitud], 18);
    }

    listaDispositivos.forEach(log => {
        const marker = L.marker([log.latitud, log.longitud]);
        
        let filtroColor = "hue-rotate(140deg) brightness(1.2)"; // Verde
        if (log.dispositivoId === 101) {
            filtroColor = "hue-rotate(320deg) saturate(3) brightness(1.2)"; // Rojo eléctrico
        }
        
        marker.on('add', function() {
            marker._icon.style.filter = filtroColor;
        });

        marker.bindPopup(`
            <div style="font-family: Arial, sans-serif;">
                <b style="color: #38bdf8;">Dispositivo ID:</b> ${log.dispositivoId}<br>
                <b>🌡️ Temp:</b> ${log.temperatura} °C<br>
                <b>📅 Reporte:</b> ${new Date(log.fechaCreacion).toLocaleString()}
            </div>
        `);
        markersGroup.addLayer(marker);
    });

    document.getElementById('txtStatus').innerText = `${listaDispositivos.length} en línea`;
    
    let htmlLista = "<ul>";
    listaDispositivos.forEach(d => {
        htmlLista += `<li><b>ID: ${d.dispositivoId}</b><br>🌡️ Temp: <span style="color: #38bdf8; font-weight: bold;">${d.temperatura}°C</span></li>`;
    });
    htmlLista += "</ul>";
    document.getElementById('deviceList').innerHTML = htmlLista;
}

// Carga inicial al abrir la página
inicializarTelemetria();

// Auto-refresh configurado a 1 minuto (60000 ms)
setInterval(function() {
    const slider = document.getElementById('timeSlider');
    const estabaAlFinal = (parseInt(slider.value) === fechasUnicasOrdenadas.length - 1);

    fetch('/api/logGanado')
        .then(response => response.json())
        .then(data => {
            if (data.length === 0) return;

            todosLosLogs = data.sort((a, b) => new Date(a.fechaCreacion) - new Date(b.fechaCreacion));
            fechasUnicasOrdenadas = todosLosLogs.map(log => log.fechaCreacion);

            slider.max = fechasUnicasOrdenadas.length - 1;
            document.getElementById('endTimeLabel').innerText = new Date(fechasUnicasOrdenadas[fechasUnicasOrdenadas.length - 1]).toLocaleString();

            if (estabaAlFinal) {
                slider.value = fechasUnicasOrdenadas.length - 1;
                renderizarMapaEnInstante(fechasUnicasOrdenadas.length - 1);
            }
        })
        .catch(error => console.error("Error en auto-refresh:", error));
}, 60000);
