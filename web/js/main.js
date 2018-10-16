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
        "<a id=\"myLink4\" href=\"#\" onclick=\"javascript:deleteElement('" + positionName + "_organization" + organizationCounter + "');return false;\"><img src=\"img/remove.png\"> Удалить организацию</a>" +
        "<div class=\"education-block\">" +
        "<div class=\"resume-group group-wide\">" +
        "<span>Название " + nameOrganization + " :</span>" +
        "<input type=\"text\" class=\"resume-input\" name=\"" + positionName + "_organization" + organizationCounter + "_1name\" required>" +
        "</div>" +
        "<div class=\"resume-group group-wide\">" +
        "<span>Сайт " + nameOrganization + " :</span>" +
        "<input type=\"text\" class=\"resume-input\" name=\"" + positionName + "_organization" + organizationCounter + "_2url\">" +
        "</div>" +
        "<br>" +
        "<a id=\"myLink1\" href=\"#\" onclick=\"javascript:addPosition('" + positionName + "_organization" + organizationCounter + "', 'fieldset', '" + positionName + "_organization" + organizationCounter + "');return false;\"><img src=\"img/add.png\"> Добавить позицию</a>" +
        "<p>";
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
        "<a id=\"myLink4\" href=\"#\" onclick=\"javascript:deleteElement('" + positionName + "_position" + positionCounter + "');return false;\"><img src=\"img/remove.png\"> Удалить позицию</a>" +
        "<div class=\"resume-group group-wide\">" +
        "<span> Дата начала: </span>" +
        "<input type=\"date\" name=\"" + positionName + "_position" + positionCounter + "_2startDate\" required>" +
        "<span> Дата окончания: </span>" +
        "<input type=\"date\" name=\"" + positionName + "_position" + positionCounter + "_3endDate\">" +
        "</div>" +
        "<div class=\"resume-group group-wide\">" +
        "<span>Должность:</span>" +
        "<input type=\"text\" class=\"resume-input\" name=\"" + positionName + "_position" + positionCounter + "_1title\" required>" +
        "</div>" +
        "<div class=\"resume-group group-wide\">" +
        "<span>Описание:</span>" +
        "<textarea type=\"text\" class=\"resume-input\" name=\"" + positionName + "_position" + positionCounter + "_4description\"></textarea>" +
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