var code = document.getElementById("findByCode");

if (code !== null) {
    code.addEventListener("keyup", function (event) {
        console.log(code.value);
        $.ajax({
            type: 'GET',
            url: "/buscarCode",
            data: "id=" + parseInt(code.value),
            success: function (data, textStatus, jqXHR) {
                parent.location.href = "/buscarCode?id="+parseInt(code.value);
            }
        });
    });
}


