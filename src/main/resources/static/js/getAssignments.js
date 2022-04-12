let positionOfThisUser = null;
$(function () {
    $("#getAssignmentsBtn").click(function () {//все создданные мной
        $.ajax({
            type: "GET",
            url: "/assignments/getAss",
            contentType: "application/json; charset=utf-8",
            data: null,
            success: function (response) {
                console.log(response);
                var trHTML = '';
                $("tr:has(td)").remove();
                $.each(response, function (i, item) {
                    var aid = item.aid;
                    var positionWhoCreated = item.whoCreated.position;
                    trHTML += '<tr><td id="' + aid + '">' + item.aid +
                        '</td><td>' + item.whoCreated.name + ' ' + item.whoCreated.surname +
                        '</td><td>' + item.whoAssignee.name + ' ' + item.whoAssignee.surname +
                        '</td><td>' + item.status +
                        '</td><td>' + item.delegated +
                        '</td><td>';
                    if (positionWhoCreated === positionOfThisUser){
                        trHTML+= '<input type="button" onclick="sign('+aid+')" value="Подписать как '+ positionWhoCreated+'" />\n'+
                        '<input type="button" onclick="cancelAssignment('+aid+')" value="Отменить" />\n' +
                          '<input type="button" onclick="deleteAssignment('+aid+')" value="Удалить" />\n' +
                         '<input type="button" onclick="improve('+aid+')" value="На доработку" />\n' + '</td><td>';
                    }
                        trHTML+='</td><td>' + '<input type="button" onclick="location.href=\'/assignments/del/' + aid + '\';" value="Скачать документ" />\n'
                        + '</td></tr>';
                });
                $('#assignmentTable').append(trHTML);
                if (response.length === 0) {
                    alert("Нет отданных Вами поручений")
                }

            },
            error: function (d) {
                console.log(d);
            }
        });
    });
    $("#getDelegatedAssignmentsBtn").click(function () {//делегированные мне
        $.ajax({
            type: "GET",
            url: "/assignments/delegatedToMe",
            contentType: "application/json; charset=utf-8",
            data: null,
            success: function (response) {
                console.log(response);
                var trHTML = '';
                $("tr:has(td)").remove();
                $.each(response, function (i, item) {
                    var delegatedID = item.id;
                    trHTML += '<tr><td>' + item.assignment.aid +
                        '</td><td>' + item.assignment.whoCreated.name + ' ' + item.assignment.whoCreated.surname +
                        '</td><td>' + item.assignment.whoAssignee.name + ' ' + item.assignment.whoAssignee.surname +
                        '</td><td>' + item.assignment.status +
                        '</td><td>' + item.assignment.delegated + '<input type="button" onclick="deleteDelegation(' + delegatedID + ')" value="Отказаться" />\n' +
                        '</td><td>' + item.newAssignee.name + ' ' + item.newAssignee.surname
                        + '</td></tr>';
                });
                $('#delegatedAssignmentsToMe').append(trHTML);
                if (response.length === 0) {
                    alert("Нет делегированных Вам поручений")
                }

            },
            error: function (d) {
                console.log(d);
            }
        });
    });
    $("#getGivenAssignmentsBtn").click(function () {//назначенные мне
        $.ajax({
            type: "GET",
            url: "/assignments/getGivenToMe ",
            contentType: "application/json; charset=utf-8",
            data: null,
            success: function (response) {
                console.log(response);
                var trHTML = '';
                $("tr:has(td)").remove();
                var numRow = -1;
                $.each(response, function (i, item) {
                    positionWhoAssignee = item.whoAssignee.position;
                    var aid = item.aid;
                    numRow=numRow+1;
                    trHTML += '<tr rowNum="'+numRow+'"><td>' + item.aid +
                        '</td><td>' + item.whoCreated.name + ' ' + item.whoCreated.surname +
                        '</td><td>' + item.whoAssignee.name + ' ' + item.whoAssignee.surname +
                        '</td><td>' + item.status +
                        '</td><td>' + item.delegated + '</td><td>';
                    if (positionWhoAssignee === positionOfThisUser){
                        trHTML+= '<input type="button" onclick="sign('+aid+')" value="Подписать как '+ positionWhoAssignee+'" />\n'+
                            '<input type="button" onclick="improve('+aid+')" value="На доработку" />\n' +
                            '<form id="formElem" enctype="multipart/form-data" name="formelem">' +
                            '<input type="file" name="fileElem" id="uploadFileNew" value="Загрузить документ" />\n' +
                            '<input type="button" onclick="uploadDocument('+aid+ ',' +numRow+')" value="Отправить документ" />\n' +
                            '</form>'+
                            '<input type="button" onclick="location.href=\'/docByAssId/' + aid + '\'" value="Посмотреть документ" />\n';
                    }
                        trHTML+='</td><td>' +
                        '</td></tr>';
                });
                $('#givenAssignments').append(trHTML);
                if (response.length === 0) {
                    alert("Нет назначенных Вам поручений")
                }

            },
            error: function (d) {
                console.log(d);
            }
        });
    });
    $(function () {
        $.ajax({
            type: "GET",
            url: "/getMyName",
            contentType: "application/json",
            success: function (resp) {
                console.log(resp);
                $('.username').html("Здравстуйте, " + resp.name + ' ' + resp.surname);
                positionOfThisUser = resp.position;
                if (resp.position === "DepartmentWorker") {
                    $('#emailAssignee').remove();
                    $('#sendAssignment').remove();
                    $('#assignmentTable').remove();
                }
                if (positionOfThisUser==="Director"){
                }

            }
        })
    });
});

function deleteDelegation(dID) {
    $.ajax({
        type: "POST",
        url: "/assignments/delegate/delete",
        data: {id: dID},
        success: function (resp) {
            console.log(resp);
            alert("Удалено");
        }
    });
}

function setInWork(dID) {
    $.ajax({
        type: "POST",
        url: "/assignments/delete",
        data: {id: dID},
        success: function (resp) {
            console.log(resp);
            alert("Удалено");
        }
    });
}
function sign(assId){
    var signs = null;
    $.ajax({
        type:"GET",
        url:""
    })
}
function deleteAssignment(id){
    console.log(id)
    $.ajax({
        type: "POST",
        url: "/assignments/delete",
        data: {id:id},
        success: function (response) {
            alert("Удалено")
        },
        error: function (d) {
            console.log(d);
        }
    });
}
function cancelAssignment(id){
    console.log(id)
    $.ajax({
        type: "POST",
        url: "/assignments/cancel",
        data: {id:id},
        success: function (response) {
            alert("Отменено")
        },
        error: function (d) {
            console.log(d);
        }
    });
}
function improve(aid){
    console.log(aid);
    $.ajax({
        type: "POST",
        url: "/assignments/improve",
        data: {id:id},
        success: function (response) {
            alert("Отправлено на доработку")
        },
        error: function (d) {
            console.log(d);
        }
    });
}
function done(aid){

}
function uploadDocument(aid,numRow){
    console.log(aid, numRow);
    var data = new FormData();
    var arr2 = $('input[type=file]');
    var myInput = arr2[numRow+1];
    console.log(arr2);
    console.log(myInput);
    var file = myInput.files[0];
    console.log(file);
    data.append("id",aid);
    data.append("formElem",file);//$('input[type=file]')[numRow].files[0]
    for (var pair of data.entries()) {
        console.log(pair[0]+ ', ' + pair[1]);
    }
    $.ajax({
        url: "/docs/upload",
        type: 'POST',
        data: data,
        processData: false, // tell jQuery not to process the data
        contentType: false, // tell jQuery not to set contentType
        cache: false,
        success: function(res) {
            console.log(res);
        },

        error: function(res) {
            console.log('ERR: ' + res.toString());
        }
    });
}
function postNewSign(aid){
    $.ajax({
        type: "POST",
        url: "/newSign",
        data: {"assId":aid},
        success: function (response) {
            console.log(response)
        },
        error: function (d) {
            console.log(d);
        }
    });

// function getDoc(assId){
//     fetch('http://localhost:8082')
//         .then(resp => resp.blob())
//         .then(blob => {
//             const url = window.URL.createObjectURL(blob);
//             const a = document.createElement('a');
//             a.style.display = 'none';
//             a.href = url;
//             // the filename you want
//             a.download = 'pic.png';
//             document.body.appendChild(a);
//             a.click();
//             window.URL.revokeObjectURL(url);
//             alert('your file has downloaded!');
//         });
    // $.ajax({
    //     type:"GET",
    //     url:"/getDocByAssId",
    //     data: {"assId": assId},
    //     contentType:""
    //     success:function (response){
    //         console.log(response);
    //
    //         //file.readAsDataURL(response);
    //         //console.log(file);
    //         //console.log(response);
    //     },
    //     error:function (resp){
    //         console.log(resp);
    //     }
    // });
}
