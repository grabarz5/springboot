var apiLocation, deviceList;
$(document).ready(() => {
	apiLocation = $('#apiLocation')[0].value;
	deviceList = $('#devicesList');
	$('#devicesList').html("<tr><td colspan='4'>Please wait...</td></tr>");
	loadDevices();
});

const addDevice = () => {
	var response = new XMLHttpRequest();
	var parameters = {
		"name": $('#inputName')[0].value,
		"ip": $('#inputIP')[0].value,
		"mac": $('#inputMAC')[0].value
	};

	response.open('POST', apiLocation + '/add', true);
	response.setRequestHeader("Content-type", "application/json");
	response.onreadystatechange = function () {
		if (this.readyState == 4 && this.status == 200) {
			console.log(response.responseText);
		}
	}
	response.send(JSON.stringify(parameters));
	$('#addDeviceForm')[0].reset();
	reloadDevices();
}

const removeAllDevices = () => {
	if (confirm("Do you really want to delete ALL devices?")) {
		var response = new XMLHttpRequest();
		response.open('GET', apiLocation + "/clean", true);
		response.send();
	}
	reloadDevices();
}

const loadDevices = () => {
	var response = new XMLHttpRequest();
	response.open('GET', apiLocation + "/list", true);
	response.onload = function () {
		var data = JSON.parse(this.response);

		if (this.status >= 200 && this.status < 400) {
			addListDevice(data);
		} else {
			$('#devicesList').html("<tr><td colspan='4'>Something is wrong...</td></tr>");
		}
	}
	response.send();
}

const addListDevice = (devices) => {
	var html = '';
	devices.forEach(({name,ip,mac}, i) => {
		html += `<tr>
		<th scope="row">${i+1}</th>
		<td>${name}</td>
		<td>${ip}</td>
		<td>${mac}</td>
	</tr>`});
	deviceList.html(html);
}

const reloadDevices = () => loadDevices();