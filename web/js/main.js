/**
 *Add organization block which contain's fields: organization name and it's url
 * @param {string} currentPositionNameId
 * @param {string} elementTag
 * @param {string} positionName
 * @param {string} nameOrganization
 * @constant {object} currentPosition - current position to create organization block
 * @constant {object} newPosition - new position to create organization block
 * @constant {object} organizationCounterElement -  value for counting organizations
 * @let {number} organizationCounter - counter for organizations
 */
function addOrganization(currentPositionNameId, elementTag, positionName, nameOrganization) {
    const currentPosition = document.getElementById(currentPositionNameId);
    const newPosition = document.createElement(elementTag);
    const organizationCounterElement = document.getElementById('organizationCounter');
    let organizationCounter = parseInt(organizationCounterElement.value);
    newPosition.setAttribute('id', positionName + "_organization" + organizationCounter);
    newPosition.innerHTML =
        "<div class=\"form-group mt-2 box-shadow-blue\">" +
        "<div class=\"card mt-2\">" +
        "<div class=\"card-body\">" +
        "<div class=\"form-group\">" +
        "<a id=\"myLink4\" href=\"#\" onclick=\"javascript:deleteElement('" + positionName + "_organization" + organizationCounter + "');return false;\"><i class=\"fa fa-times\" aria-hidden=\"true\" style=\"color: #D780F1;\"></i><h7 class=\"mx-2\">Удалить организацию</h7></a>" +
        "<div class=\"row\">" +
        "<div class=\"col\">" +
        "<label>Название " + nameOrganization + " :</label>" +
        "<input type=\"text\" class=\"form-control\" name=\"" + positionName + "_organization" + organizationCounter + "_1name\" required>" +
        "</div>" +
        "<div class=\"col\">" +
        "<label>Сайт " + nameOrganization + " :</label>" +
        "<input type=\"text\" class=\"form-control\" name=\"" + positionName + "_organization" + organizationCounter + "_2url\">" +
        "</div>" +
        "</div>" +
        "</div>" +
        "<div class=\"form-group\">" +
        "<a id=\"myLink1\" href=\"#\" onclick=\"javascript:addPosition('" + positionName + "_organization" + organizationCounter + "', 'fieldset', '" + positionName + "_organization" + organizationCounter + "');return false;\"><i class=\"fa fa-plus\" aria-hidden=\"true\" style=\"color: #3D6BE9;\"></i><h7 class=\"mx-2\">Добавить позицию</h7></a>" +
        "</div>" +
        "</div>" +
        "</div>" +
        "</div>";
    currentPosition.appendChild(newPosition);
    organizationCounterElement.value = organizationCounter + 1;
}

/**
 * Add position block to organization
 * @param {string} currentPositionNameId
 * @param {string} elementTag
 * @param {string} positionName
 * @constant {object} currentPosition - current position to create position block
 * @constant {object} newPosition - new position to position block
 * @constant {object} positionCounterElement -  value for counting positions
 * @let {number} positionCounter - counter for positions
 */
function addPosition(currentPositionNameId, elementTag, positionName) {
    const currentPosition = document.getElementById(currentPositionNameId);
    const newPosition = document.createElement(elementTag);
    const positionCounterElement = document.getElementById('positionCounter');
    let positionCounter = parseInt(positionCounterElement.value);
    newPosition.setAttribute('id', positionName + "_position" + positionCounter);
    newPosition.innerHTML =
        "<div class=\"card mt-2\">" +
        "<div class=\"card-header\">" +
        "<div class=\"form-group\">" +
        "<a id=\"myLink4\" href=\"#\" onclick=\"javascript:deleteElement('" + positionName + "_position" + positionCounter + "');return false;\"><i class=\"fa fa-times\" aria-hidden=\"true\" style=\"color: #D780F1;\"></i><h7 class=\"mx-2\">Удалить позицию</h7></a>" +
        "<div class=\"row\">" +
        "<div class=\"col\">" +
        "<label> Дата начала: </label>" +
        "<input type=\"date\" class=\"form-control\" name=\"" + positionName + "_position" + positionCounter + "_2startDate\" required>" +
        "</div>" +
        "<div class=\"col\">" +
        "<label> Дата окончания: </label>" +
        "<input type=\"date\" class=\"form-control\" name=\"" + positionName + "_position" + positionCounter + "_3endDate\">" +
        "</div>" +
        "</div>" +
        "</div>" +
        "<div class=\"form-group\">" +
        "<label>Должность:</label>" +
        "<input type=\"text\" class=\"form-control\" name=\"" + positionName + "_position" + positionCounter + "_1title\" required>" +
        "</div>" +
        "<div class=\"form-group\">" +
        "<label>Описание:</label>" +
        "<div class=\"input-group\">" +
        "<textarea type=\"text\" class=\"form-control my-1\" name=\"" + positionName + "_position" + positionCounter + "_4description\"></textarea>" +
        "</div>" +
        "</div>" +
        "</div>" +
        "</div>";
    currentPosition.appendChild(newPosition);
    positionCounterElement.value = positionCounter + 1;
}

/**
 * Delete element
 * @param {string} elementId
 */
function deleteElement(elementId) {
    document.getElementById(elementId).remove();
}

/**
 *Add single empty textarea input
 * @param {string} currentPositionNameId - section name
 * @param {string} newPositionHtml
 * @constant {object} currentPosition
 * @constant {object} newPosition
 */
function addSinglePosition(currentPositionNameId, newPositionHtml) {
    const currentPosition = document.getElementById(currentPositionNameId);
    const newPosition = document.createElement('div');
    newPosition.innerHTML = newPositionHtml;
    currentPosition.appendChild(newPosition);
}