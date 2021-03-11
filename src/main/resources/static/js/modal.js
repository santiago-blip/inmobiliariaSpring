//var containerModal = document.getElementById("containerModal");
//var modalSession = document.getElementById("modalSession");
//var modalRegistro = document.getElementById("modalRegistro");

var iniciarSession = document.getElementById("iniciarSession");
if (iniciarSession !== null) {
    var containerModal = document.getElementById("containerModal");
    var modalSession = document.getElementById("modalSession");
    iniciarSession.addEventListener("click", function () {
        containerModal.classList.toggle("container-modalActive");
        modalSession.classList.toggle("modalVisible");
    });
}

//LINKS CAMBIO MODAL
var registro = document.getElementById("linkRegistro");
var session = document.getElementById("linkSession");
var sesion = document.getElementById("linkSesion");
var contra = document.getElementById("linkContra");
if (registro && session && contra !== null) {
    var modalS = document.getElementById("modalSession");
    var modalR = document.getElementById("modalRegistro");
    var modalC = document.getElementById("modalContraseña");
    registro.addEventListener("click", () => {
        console.log("dio en registro");
        modalS.classList.toggle("modalVisible");
        modalR.classList.toggle("modalVisible");
    });
    session.addEventListener("click", () => {
        modalS.classList.toggle("modalVisible");
        modalR.classList.toggle("modalVisible");
    });
    sesion.addEventListener("click", () => {
        modalS.classList.toggle("modalVisible");
        modalC.classList.toggle("modalVisible");
    });
    contra.addEventListener("click", () => {
        if (modalS.classList.contains("modalVisible")) {
            modalS.classList.toggle("modalVisible");
            modalC.classList.toggle("modalVisible");
        }
    });
}

//Cerrar Modales.
window.addEventListener("click", function (e) {
    var containerModal = document.getElementById("containerModal");
    var modalSession = document.getElementById("modalSession");
    var modalRegistro = document.getElementById("modalRegistro");
    var modalC = document.getElementById("modalContraseña");
    console.log(e);
    if (e.target === containerModal) {
        if (modalSession.classList.contains("modalVisible")) {
            modalSession.classList.toggle("modalVisible");
            containerModal.classList.toggle("container-modalActive");
        }
        if (modalRegistro.classList.contains("modalVisible")) {
            modalRegistro.classList.toggle("modalVisible");
            containerModal.classList.toggle("container-modalActive");
        }
        if (modalC.classList.contains("modalVisible")){
            modalC.classList.toggle("modalVisible");
            containerModal.classList.toggle("container-modalActive");
        }
    }
});

