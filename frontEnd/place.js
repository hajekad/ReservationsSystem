async function placeGet() {
    const id = document.getElementById('id-input-get').value;
    try {
      const response = await fetch(`http://localhost:6060/place?id=${id}`);
      if (response.ok) {
        const place = await response.json();
        console.log("Place:\n" + JSON.stringify(place, null, 2));
        document.getElementById('responseGet').value = JSON.stringify(place, null, 2);
      } else {
        console.error(`Error: ${response.status} ${response.statusText}`);
        document.getElementById('responseGet').value = `Error: ${response.status} ${response.statusText}`;
      }
    } catch (error) {
      console.error(error);
      document.getElementById('responseGet').value = error.toString();
    }
  }
  
async function createPlace() {
    const latitude = document.getElementById('id-update').value;
    const longitude = document.getElementById('id-update').value;

    const createPlaceDto = {
        latitude: latitude, 
        longitude: longitude
    };
  
    try {
      const response = await fetch('http://localhost:6060/place', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(createPlaceDto)
      });
      if (response.ok) {
        const place = await response.json();
        console.log("Place:\n" + JSON.stringify(place, null, 2));
        document.getElementById('responsePost').value = JSON.stringify(place, null, 2);
      } else {
        console.error(`Error: ${response.status} ${response.statusText}`);
        document.getElementById('responsePost').value = `Error: ${response.status} ${response.statusText}`;
      }
    } catch (error) {
      console.error(error);
      document.getElementById('responsePost').value = error.toString();
    }
  }
  
async function placeDelete() {
    const id = document.getElementById('id-input-delete').value;
    try {
      const response = await fetch(`http://localhost:6060/place?id=${id}`, {
        method: 'DELETE'
      });
      if (response.ok) {
        console.log(`Status: ${response.status} ${response.statusText}`);
        document.getElementById('responseDelete').value = `${response.status} ${response.statusText}`;
      } else {
        console.error(`Error: ${response.status} ${response.statusText}`);
        document.getElementById('responseDelete').value = `Error: ${response.status} ${response.statusText}`;
      }
    } catch (error) {
      console.error(error);
      document.getElementById('responseDelete').value = error.toString();
    }
  }
  
async function updatePlace() {
    const id = document.getElementById('id-update').value;
    const latitude = document.getElementById('id-update').value;
    const longitude = document.getElementById('id-update').value;
    const trainings = document.getElementById('id-update').value;

    const placeDto = {
        id: id, 
        latitude: latitude, 
        longitude: longitude, 
        trainings: trainings 
    };
  
    console.log("Sending: " + JSON.stringify(placeDto));
  
    try {
      const response = await fetch('http://localhost:6060/place', {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(placeDto)
      });
      if (response.ok) {
        const place = await response.json();
        console.log("Place:\n" + JSON.stringify(place, null, 2));
        document.getElementById('responsePost').value = JSON.stringify(place, null, 2);
      } else {
        console.error(`Error: ${response.status} ${response.statusText}`);
        document.getElementById('responsePost').value = `Error: ${response.status} ${response.statusText}`;
      }
    } catch (error) {
      console.error(error);
      document.getElementById('responsePost').value = error.toString();
    }
  }
  
  
function addInput() {
    const inputContainer = document.getElementById('input-container');
    const input = document.createElement('input');
    input.type = 'number';
    input.className = 'trainingInput';
    inputContainer.appendChild(input);
  }
  