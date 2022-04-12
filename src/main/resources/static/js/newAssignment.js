$(function () {
    $("#sendAssignment").click(function () {
        var assigneeEmail = $("#emailAssignee").val();
        console.log(assigneeEmail)
        let doc = document.getElementById("fileAss").files[0];
        $.ajax({
            type: "POST",
            url: "/assignments/new",
            data: {assigneeEmail:assigneeEmail},
            success: function (response) {
                alert("Поручение отпралено")
            },
            error: function (d) {
                console.log(d);
            }
        });
    });
});