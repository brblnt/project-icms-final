function checkboxValidate() {

  var checkBoxAdmin = document.getElementById("editRoleAdmin");
  var checkBoxSuper = document.getElementById("editRoleSup");

//TODO: little correction if admin checked change to suser
  if (checkBoxAdmin.checked == true){
    checkBoxSuper.checked = false;
  } else if (checkBoxSuper.checked == true){
      checkBoxAdmin.checked = false;
  }
}

form.addEventListener('submit', () => {
    if(document.getElementById("editEnabled").checked) {
        document.getElementById('editEnabledHidden').disabled = true;
    }
    if(document.getElementById("passwordChangedCheck").checked) {
            document.getElementById('passwordChangedCheckHidden').disabled = true;
    }
    if(document.getElementById("editExpired").checked) {
            document.getElementById('editExpiredHidden').disabled = true;
    }
    if(document.getElementById("editLocked").checked) {
            document.getElementById('editLockedHidden').disabled = true;
    }
    if(document.getElementById("editCredentialsExpired").checked) {
            document.getElementById('editCredentialsExpiredHidden').disabled = true;
    }
})



function worksheetCustomerCreate() {
        var url = '/worksheet/customers/create';
        $("#frameCustomer").load(url,$("#frameCustomer").serialize());
        toastTriggerCreateInfo();
}
function worksheetDealerCreate() {
        var url = '/worksheet/dealers/create';
        $("#frameDealer").load(url,$("#frameDealer").serialize());
        toastTriggerCreateInfo();
}
function worksheetFaultCreate() {
        var url = '/worksheet/fault/create';
        $("#frameFault").load(url,$("#frameFault").serialize());
        toastTriggerCreateInfo();
}
function worksheetProductCreate() {
        var url = '/worksheet/products/create';
        $("#frameProduct").load(url,$("#frameProduct").serialize());
        toastTriggerCreateInfo();
}
function worksheetObjectCreate() {
        var url = '/worksheet/items/create';
        $("#frameObject").load(url,$("#frameObject").serialize());
        toastTriggerCreateInfo();
}
function worksheetServiceCreate() {
        var url = '/worksheet/services/create';
        $("#frameService").load(url,$("#frameService").serialize());
        toastTriggerCreateInfo();
}
function workerCreate() {
        var url = '/super-user/settings/workers/create';
        $("#frameWorker").load(url,$("#frameWorker").serialize());
        toastTriggerCreateInfo();
}

function newWorksheetCustomerSelected() {
        var customerID = document.querySelector("#customerSelect").value;
        var url = '/worksheet/new/customer/select/'+customerID;
        $("#itemFragmentNewWorksheet").load(url,$("#itemFragmentNewWorksheet").serialize());
}
function addFaultsForWorksheet() {
    var faultTextBox = document.querySelector("#faultSelect");
    var faultIdList = document.querySelector("#faultIdList");
    var faultTextBoxLabel = document.querySelector("#faultListLabel");
    var faultID = faultTextBox.value;
    var faultText = faultTextBox.options[faultTextBox.selectedIndex].text;

    var faultList = document.querySelector("#faultList");

    if (faultID > 0) {
        if (!faultIdList.value.includes(faultID)){
                document.querySelector("#newWorksheetButton").disabled = false;
                faultIdList.value = faultIdList.value+","+faultID;
                faultTextBoxLabel.hidden = false;
                faultList.hidden = false;
                faultList.value  = faultList.value + " " + faultText;
        }
    }

}

function showToast() {
    const toast = new bootstrap.Toast(document.getElementById('liveToast'));
    toast.show();
}

function toastTriggerCreateInfo() {
    $("#toastFragment").load("/toast/info/NFO/na/worksheetCustomerNewUserInfo/primary");
    showToast();
}

function toastTriggerCustomerUpdateSuccessful() {
    $("#toastFragment").load("/toast/info/NFO/na/worksheetCustomerUpdateSuccessful/success");
    showToast();
}

function toastTriggerCustomerUpdateUnsuccessful() {
    $("#toastFragment").load("/toast/danger/HIBA/na/worksheetCustomerUpdateUnSuccessful/danger");
    showToast();
}

function noSelectedElement() {
    $("#toastFragment").load("/toast/warning/Figyelem/na/noSelected/warning");
    showToast();
}

function toastTriggerDeleteSuccessful() {
    $("#toastFragment").load("/toast/info/NFO/na/successfulDelete/success");
    showToast();
}
function toastTriggerDeleteUnsuccessful() {
    $("#toastFragment").load("/toast/danger/HIBA/na/unsuccessfulDelete/danger");
    showToast();
}
function toastTriggerCompanyUpdateSuccessful() {
    $("#toastFragment").load("/toast/info/NFO/na/companySaveSuccessful/success");
    showToast();
}
function toastTriggerCompanyUpdateUnsuccessful() {
    $("#toastFragment").load("/toast/warning/Figyelem/na/companySaveUnsuccessful/warning");
    showToast();
}
function toastTriggerNoCustomID() {
    $("#toastFragment").load("/toast/danger/HIBA/na/customIdNotUnique/danger");
    showToast();
}

function showInformation() {
    const element = document.querySelector("#toastTrigger");
    if (element.classList.contains("successful-toast-update")) {
        toastTriggerCustomerUpdateSuccessful();
    }
    else if (element.classList.contains("unsuccessful-toast-update")) {
        toastTriggerCustomerUpdateUnsuccessful();
    }
    else if (element.classList.contains("successful-toast-delete")) {
        toastTriggerDeleteSuccessful();
    }
    else if (element.classList.contains("unsuccessful-toast-delete")) {
        toastTriggerDeleteUnsuccessful();
    }
    else if (element.classList.contains("successful-toast-save-company")) {
        toastTriggerCompanyUpdateSuccessful();
    }
    else if (element.classList.contains("unsuccessful-toast-save-company")) {
        toastTriggerCompanyUpdateUnsuccessful();
    }
    else if (element.classList.contains("unsuccessful-toast-workers-update")) {
        toastTriggerNoCustomID();
    }
}


function removeTableRow(x) {
    document.getElementById(x).remove();
    toastTriggerDeleteSuccessful();
}

function addFaultsForExistWorksheet() {
    var faultTextBox = document.querySelector("#faultSelect");
    var faultID = faultTextBox.value;
    var faultText = faultTextBox.options[faultTextBox.selectedIndex].text;

    var faultTable = document.querySelector("#faultList");
    if (faultID > 0) {
        var row = faultTable.insertRow(1);
        var cell0 = row.insertCell(0);
        var cell1 = row.insertCell(1);
        var cell2 = row.insertCell(2);
        row.id = "255"+faultID+"12";
        cell0.innerHTML = faultID;
        cell1.innerHTML = faultText;
        cell2.innerHTML = '<button  type="button" onclick="removeTableRow(255'+faultID+'12)" class="btn btn-danger"><i class="bi bi-dash"></i></button>';
        cell0.hidden = true;
    }
}

function addServicesForExistWorksheet() {
    var serviceTextBox = document.querySelector("#serviceSelect");
    var serviceID = serviceTextBox.value;
    var serviceText = serviceTextBox.options[serviceTextBox.selectedIndex].text;

    var serviceTable = document.querySelector("#serviceList");
    if (serviceID > 0) {
        var row = serviceTable.insertRow(1);
        var cell0 = row.insertCell(0);
        var cell1 = row.insertCell(1);
        var cell2 = row.insertCell(2);
        row.id = "128"+serviceID+"14";
        cell0.innerHTML = serviceID;
        cell1.innerHTML = serviceText;
        cell2.innerHTML = '<button  type="button" onclick="removeTableRow(128'+serviceID+'14)" class="btn btn-danger"><i class="bi bi-dash"></i></button>';
        cell0.hidden = true;
    }
}
function addProductsForExistWorksheet() {
    var productTextBox = document.querySelector("#productSelect");
    var productID = productTextBox.value;
    var productText = productTextBox.options[productTextBox.selectedIndex].text;

    var productTable = document.querySelector("#productList");
    if (productID > 0) {
        var row = productTable.insertRow(1);
        var cell0 = row.insertCell(0);
        var cell1 = row.insertCell(1);
        var cell2 = row.insertCell(2);
        row.id = "998"+productID+"15";
        cell0.innerHTML = productID;
        cell1.innerHTML = productText;
        cell2.innerHTML = '<button  type="button" onclick="removeTableRow(998'+productID+'15)" class="btn btn-danger"><i class="bi bi-dash"></i></button>';
        cell0.hidden = true;
    }
}

function getDataFromTableForInput() {
    var table = document.getElementById("faultList");
    var faultIdList = document.querySelector("#faultIDList");
    faultIdList.value = "0";
    for (var i = 1, row; row = table.rows[i]; i++) {
       faultIdList.value = faultIdList.value +";"+ row.cells[0].innerText;
    }

    var table1 = document.getElementById("serviceList");
    var serviceIdList = document.querySelector("#serviceIDList");
    serviceIdList.value = "0";
    for (var i = 1, row; row = table1.rows[i]; i++) {
       serviceIdList.value = serviceIdList.value +";"+ row.cells[0].innerText;
    }

    var table2 = document.getElementById("productList");
    var productIdList = document.querySelector("#productIDList");
    productIdList.value = "0";
    for (var i = 1, row; row = table2.rows[i]; i++) {
       productIdList.value = productIdList.value +";"+ row.cells[0].innerText;
    }
    var button = document.querySelector("#save-form-worksheet");
    button.click();
}