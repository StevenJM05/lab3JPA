const modal = document.getElementById('profileModal');
    const openModalBtn = document.querySelector('.appointment-btn');
    

    openModalBtn.addEventListener('click', () => {
        modal.style.display = 'flex';
    });


    function cerrar(){
        modal.style.display = 'none';
    }

    