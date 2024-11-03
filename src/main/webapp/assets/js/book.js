const modal = document.getElementById('profileModal');
const modalActualizar = document.getElementById('modalActu');
const modalEli  = document.getElementById('modalEliminar');

    const openModalBtn = document.querySelector('.appointment-btn');
    const actuaModalBtn = document.querySelector('.btn-actualizar')
    const eliminarModalBtn = document.querySelector('.btn-eliminar');


    openModalBtn.addEventListener('click', () => {
        modal.style.display = 'flex';
    });

    actuaModalBtn.addEventListener('click', () =>{
        modalActualizar.style.display = 'flex';
    });

    eliminarModalBtn.addEventListener('click', () =>{
        modalEli.style.display = 'flex';
    });

    function cerrar(){
        modal.style.display = 'none';
        modalActualizar.style.display = 'none';
        modalEli.style.display = 'none';
    }

    