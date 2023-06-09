$(document).ready(function() {
    $('td[contenteditable]').on('keydown', function(event) {
        if (event.keyCode === 13) {
            let cell = $(this);
            let row = cell.parent().index();
            let col = cell.index() - 1;

            let currentURL = window.location.href;
            let tableID = currentURL.split('/').pop();
            let updateURL = "/tables/update/" + tableID;

            sendData(updateURL, row, col, cell.text(), function(data) {cell.text(data)});

            event.preventDefault();
            cell.blur();
        }
    });
});
function sendData(url, row, col, expression, callback) {
    $.ajax({
        url: url,
        method: 'POST',
        data: { rowIndex: row, colIndex: col, expression: expression },
        success: function (data) {
            callback(data);
        },
        error: function(xhr, status, error) {
            console.log("Ошибка при отправке HTTP-запроса");
        }
    });
}
