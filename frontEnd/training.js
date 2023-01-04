async function trainingGet() {
  const id = document.getElementById('id-get').value;
  get(id, '/training');
}
  
async function createTraining() {
  const idCoach = document.getElementById('id-coach-post').value;
  const idPlace = document.getElementById('id-place-post').value;
  const dateOfTraining = convertDatetimeLocal(document.getElementById('date-of-training-post').value);
  const description = document.getElementById('description-post').value;

  const createTrainingDto = {
    idCoach: idCoach,
    idPlace: idPlace,
    dateOfTraining: dateOfTraining,
    description: description,    
  };

  create(createTrainingDto, '/training')
}
  
async function trainingDelete() {
  const id = document.getElementById('id-delete').value;
  remove(id, '/training');  
}
  
async function updateTraining() {
  const id = document.getElementById('id-update').value;
  const idCoach = document.getElementById('id-coach-update').value;
  const idPlace = document.getElementById('id-place-update').value;
  const dateOfTraining = convertDatetimeLocal(document.getElementById('date-of-training-update').value);
  const description = document.getElementById('description-update').value;
  const inputs = document.querySelectorAll('.traineesInput');
  const participatingTraineesIds = Array.from(inputs).map(input => Number(input.value));

  const trainingDto = {
    id: id,
    idCoach: idCoach,
    idPlace: idPlace,
    dateOfTraining: dateOfTraining,
    description: description,
    participatingTraineesIds: participatingTraineesIds
  };

  update(trainingDto, '/training');
}
  
function addInput() {
  const inputContainer = document.getElementById('input-container');
  const input = document.createElement('input');
  input.type = 'number';
  input.className = 'traineesInput';
  inputContainer.appendChild(input);
}

async function get(id, at) {
  try {
    const response = await fetch(`http://localhost:6060` + at + `?id=${id}`);
    if (response.ok) {
      const responseJson = await response.json();
      console.log("JSON:\n" + JSON.stringify(responseJson, null, 2));
      document.getElementById('responseGet').value = JSON.stringify(responseJson, null, 2);
    } else {
      console.error(`Error: ${response.status} ${response.statusText}`);
      document.getElementById('responseGet').value = `Error: ${response.status} ${response.statusText}`;
    }
  } catch (error) {
    console.error(error);
    document.getElementById('responseGet').value = error.toString();
  }
}
  
async function create(createDto, at) {
  try {
    const response = await fetch('http://localhost:6060' + at, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(createDto)
    });
    if (response.ok) {
      const responseJson = await response.json();
      console.log("JSON:\n" + JSON.stringify(responseJson, null, 2));
      document.getElementById('responsePost').value = JSON.stringify(responseJson, null, 2);
    } else {
      console.error(`Error: ${response.status} ${response.statusText}`);
      document.getElementById('responsePost').value = `Error: ${response.status} ${response.statusText}`;
    }
  } catch (error) {
    console.error(error);
    document.getElementById('responsePost').value = error.toString();
  }
}

async function remove(id, at) {
  try {
    const response = await fetch(`http://localhost:6060` + at + `?id=${id}`, {
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
  
async function update(dto, at) {
  console.log("Sending: " + JSON.stringify(dto));

  try {
    const response = await fetch('http://localhost:6060' + at, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(dto)
    });
    if (response.ok) {
      const responseJson = await response.json();
      console.log("JSON:\n" + JSON.stringify(responseJson, null, 2));
      document.getElementById('responseUpdate').value = JSON.stringify(responseJson, null, 2);
    } else {
      console.error(`Error: ${response.status} ${response.statusText}`);
      document.getElementById('responseUpdate').value = `Error: ${response.status} ${response.statusText}`;
    }
  } catch (error) {
    console.error("Error: one of the ids does not exist.");
    document.getElementById('responseUpdate').value = "Error: one of the ids does not exist.";
  }
}

function convertDatetimeLocal(input) { 
  const timestamp = Date.parse(input);
  
  if (isNaN(timestamp)) {
    return 'Invalid Date';
  }
  
  const date = new Date(timestamp);
  
  const year = date.getFullYear();
  const month = String(date.getMonth() + 1).padStart(2, '0');
  const day = String(date.getDate()).padStart(2, '0');
  
  const hour = String(date.getHours()).padStart(2, '0');
  const minute = String(date.getMinutes()).padStart(2, '0');
  
  return `${year}-${month}-${day} ${hour}:${minute}:00`;
}