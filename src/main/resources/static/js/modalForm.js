$("#formRegistro").submit(function (e) {
    e.preventDefault();
    var nombre = $("#usernameRe").val();
    var precio = $("#passwordRe").val();
    emailRegex = /^[-\w.%+]{1,64}@(?:[A-Z0-9-]{1,63}\.){1,125}[A-Z]{2,63}$/i;
    //Se muestra un texto a modo de ejemplo, luego va a ser un icono
    console.log($("#usernameRe").val());
    if (emailRegex.test(nombre)) {
        $("#lblnombreErr").html("<span></span>");
    } else {
        $("#lblnombreErr").html("<span style='color:red;'>El correo electrónico no es válido.</span>");
        $("#correoId").focus();
        return false;
    }
    this.submit();
});

