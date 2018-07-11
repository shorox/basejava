function addOrganization(parentId, elementTag, elementName, nameOrganization) {
    var div = document.getElementById(parentId);
    var newElement = document.createElement(elementTag);
    var s = parseInt(document.getElementById('organizationCounter').value);
    newElement.setAttribute('id', elementName + "_organization" + s);
    var html1 =

        "<a id=\"myLink4\" href=\"#\" onclick=\"javascript:deleteElement('" + elementName + "_organization" + s + "');return false;\"><img src=\"img/remove.png\"> Удалить организацию</a>" +

        "<div class=\"education-block\">" +
        " <div class=\"resume-group group-wide\">" +
        "<span>Название " + nameOrganization + " :</span>" +
        "  <input type=\"text\" class=\"resume-input\" name=\"" + elementName + "_organization" + s + "_1name\" required>" +
        "   </div>" +

        " <div class=\"resume-group group-wide\">" +
        "<span>Сайт " + nameOrganization + " :</span>" +
        "<input type=\"text\" class=\"resume-input\" name=\"" + elementName + "_organization" + s + "_2url\" >" +
        "</div>" +

        "<br>" +
        "<a id=\"myLink1\" href=\"#\" onclick=\"javascript:addPosition('" + elementName + "_organization" + s + "', 'fieldset', '" + elementName + "_organization" + s + "');return false;\"><img src=\"img/add.png\"> Добавить позицию</a>" +
        "<p>";

    newElement.innerHTML = html1;
    div.appendChild(newElement);
    document.getElementById('organizationCounter').value = s + 1;
}

function addPosition(parentId, elementTag, elementName) {
    var div = document.getElementById(parentId);
    var newElement = document.createElement(elementTag);
    var s = parseInt(document.getElementById('positionCounter').value);
    newElement.setAttribute('id', elementName + "_position" + s);
    var html1 =

        "<a id=\"myLink4\" href=\"#\" onclick=\"javascript:deleteElement('" + elementName + "_position" + s + "');return false;\"><img src=\"img/remove.png\"> Удалить позицию</a>" +

        " <div class=\"resume-group group-wide\">" +
        " <span> Дата начала: </span>" +
        "  <input type=\"date\" name=\"" + elementName + "_position" + s + "_2startDate\" required >" +
        " <span> Дата окончания: </span>" +
        "  <input type=\"date\" name=\"" + elementName + "_position" + s + "_3endDate\"   >" +
        "  </div>" +

        "<div class=\"resume-group group-wide\">" +
        " <span>Должность:</span>" +
        " <input type=\"text\" class=\"resume-input\" name=\"" + elementName + "_position" + s + "_1title\" required>" +
        "  </div>" +

        "  <div class=\"resume-group group-wide\">" +
        " <span>Описание:</span>" +
        " <textarea type=\"text\" class=\"resume-input\" name=\"" + elementName + "_position" + s + "_4description\"></textarea>" +
        "  </div>";
    newElement.innerHTML = html1;
    div.appendChild(newElement);
    document.getElementById('positionCounter').value = s + 1;
}

function deleteElement(elementId) {
    document.getElementById(elementId).remove();
}

function add_feed2(html, id) {
    var div2 = document.getElementById(id);
    var newElement2 = document.createElement('div');
    newElement2.innerHTML = html;
    div2.appendChild(newElement2);
}