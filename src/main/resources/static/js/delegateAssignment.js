$(function () {
    $("#delegateTask").click(function () {
        var assignmentId = $("#idAssignment").val();
        var newAssigneeEmail = $("#emailNewAssignee").val();
        console.log(assignmentId, newAssigneeEmail);
        $.ajax({
            type: "POST",
            url: "/assignments/delegate",
            //contentType: "application/json; charset=utf-8",
            data: {newAssigneeEmail:newAssigneeEmail, assignmentId:assignmentId},
            success: function (response) {
                alert("Поручение передано")
            },
            error: function (d) {
                console.log(d);
            }
        });
    });
});