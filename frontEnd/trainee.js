async function findMatch() {
  const range = document.getElementById('range-find-match').value;
  const id = document.getElementById('id-find-match').value;
  const username = document.getElementById('username-find-match').value;
  const password = document.getElementById('password-find-match').value;
  const email = document.getElementById('email-find-match').value;
  const firstName = document.getElementById('first-name-find-match').value;
  const secondName = document.getElementById('second-name-find-match').value;
  const skillCap = document.getElementById('skill-cap-find-match').value;
  const inputs = document.querySelectorAll('.trainingInputFindMatch');
  const trainings = Array.from(inputs).map(input => Number(input.value));
  
  const traineeDto = {
      id: id,
      username: username,
      password: password,
      email: email,
      firstName: firstName,
      secondName: secondName,
      skillCap: skillCap,
      trainings: trainings
  };

  try {
    const response = await fetch(`http://localhost:6060/trainee/bussiness?range=${range}`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(traineeDto)
    });
    if (response.ok) {
      const responseJson = await response.json();
      console.log("JSON:\n" + JSON.stringify(responseJson, null, 2));
      document.getElementById('responseFindMatch').value = JSON.stringify(responseJson, null, 2);
    } else {
      console.error(`Error: ${response.status} ${response.statusText}`);
      document.getElementById('responseFindMatch').value = `Error: ${response.status} ${response.statusText}`;
    }
  } catch (error) {
    console.error(error);
    document.getElementById('responseFindMatch').value = error.toString();
  }
}

async function assignTraining() {
  const id = document.getElementById('id-assign-training').value;
  const username = document.getElementById('username-assign-training').value;
  const password = document.getElementById('password-assign-training').value;
  const email = document.getElementById('email-assign-training').value;
  const firstName = document.getElementById('first-name-assign-training').value;
  const secondName = document.getElementById('second-name-assign-training').value;
  const skillCap = document.getElementById('skill-cap-assign-training').value;
  const inputs = document.querySelectorAll('.trainingInputAssignTraining');
  const trainings = Array.from(inputs).map(input => Number(input.value));

  const fromDL = document.getElementById('from-assign-training').value;
  const toDL = document.getElementById('to-assign-training').value;

  const from = convertDatetimeLocal(fromDL);
  const to = convertDatetimeLocal(toDL);
  
  console.log(`${fromDL} -> ${from}\n${toDL} -> ${to}`);

  const traineeDto = {
      id: id,
      username: username,
      password: password,
      email: email,
      firstName: firstName,
      secondName: secondName,
      skillCap: skillCap,
      trainings: trainings
  };

  try {
    const response = await fetch(`http://localhost:6060/trainee/bussiness?from=${from}&to=${to}`, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(traineeDto)
    });
    if (response.ok) {
      const responseJson = await response.json();
      console.log("JSON:\n" + JSON.stringify(responseJson, null, 2));
      document.getElementById('responseAssignTraining').value = JSON.stringify(responseJson, null, 2);
    } else {
      console.error(`Error: ${response.status} ${response.statusText}`);
      document.getElementById('responseAssignTraining').value = `Error: ${response.status} ${response.statusText}`;
    }
  } catch (error) {
    console.error("Error: one of the ids does not exist.");
    document.getElementById('responseAssignTraining').value = "Error: one of the ids does not exist.";
  }
}

async function traineeGet() {
  const id = document.getElementById('id-get').value;
  get(id, '/trainee');
}
  
async function createTrainee() {    
  const username = document.getElementById('username-post').value;
  const password = document.getElementById('password-post').value;
  const email = document.getElementById('email-post').value;
  const firstName = document.getElementById('first-name-post').value;
  const secondName = document.getElementById('second-name-post').value;
  const skillCap = document.getElementById('skill-cap-post').value;

  const createTraineeDto = {
      username: username,
      password: password,
      email: email,
      firstName: firstName,
      secondName: secondName,
      skillCap: skillCap,
  };

  create(createTraineeDto, '/trainee');
}

async function traineeDelete() {
  const id = document.getElementById('id-delete').value;
  remove(id, '/trainee');
}

async function updateTrainee() {
  const id = document.getElementById('id-update').value;
  const username = document.getElementById('username-update').value;
  const password = document.getElementById('password-update').value;
  const email = document.getElementById('email-update').value;
  const firstName = document.getElementById('first-name-update').value;
  const secondName = document.getElementById('second-name-update').value;
  const skillCap = document.getElementById('skill-cap-update').value;
  const inputs = document.querySelectorAll('.trainingInput');
  const trainings = Array.from(inputs).map(input => Number(input.value));
  
  const traineeDto = {
      id: id,
      username: username,
      password: password,
      email: email,
      firstName: firstName,
      secondName: secondName,
      skillCap: skillCap,
      trainings: trainings
  };

  update(traineeDto, '/trainee');
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


function addInput() {
  const inputContainer = document.getElementById('input-container');
  const input = document.createElement('input');
  input.type = 'number';
  input.className = 'trainingInput';
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
    console.error(`Error: Id not present in the database or training does not exist.`);
    document.getElementById('responseUpdate').value = `Error: Error: Id not present in the database or training does not exist.`;
  }
}