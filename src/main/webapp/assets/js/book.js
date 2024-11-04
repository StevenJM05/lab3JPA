const modal = document.getElementById('profileModal');
const modalActualizar = document.getElementById('modalActu');
const modalEli  = document.getElementById('modalEliminar');

const openModalBtn = document.querySelector('.appointment-btn');
const eliminarModalBtn = document.querySelector('.btn-eliminar');

// Abre el modal de agregar
openModalBtn.addEventListener('click', () => {
    modal.style.display = 'flex';
});

// Abre el modal de eliminación
eliminarModalBtn.addEventListener('click', () => {
    modalEli.style.display = 'flex';
});

// Función para cerrar cualquier modal abierto
function cerrar() {
    modal.style.display = 'none';
    modalActualizar.style.display = 'none';
    modalEli.style.display = 'none';
}

// Función para abrir el modal de actualización y llenar los campos
function actualizarLibro(id, titulo, autor, genero) {
    const idInput = document.getElementById('idAct');
    const tituloInput = document.getElementById('tituloAct');
    const autorInput = document.getElementById('autorAct');
    const generoInput = document.getElementById('generoAct');

    // Asigna los valores a los inputs del modal de actualización
    idInput.value =  id;
    tituloInput.value = titulo;
    autorInput.value = autor;
    generoInput.value = genero;

    // Muestra el modal de actualización
    modalActualizar.style.display = 'flex';
}

function eliminarLibro(id){
    const idInput = document.getElementById('idElim');
    idInput.value = id;
    modalEli.style.display = 'flex';
}
