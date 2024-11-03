const modal =  document.getElementById('modal');

const agregarModalBtn = document.querySelector('.btn-agregar');

agregarModalBtn.addEventListener("click", () =>{
    modal.style.display = "flex";
});

function cerrar(){
    modal.style.display = "none";
}