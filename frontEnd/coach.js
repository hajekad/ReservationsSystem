async function coachGet() {
  const id = document.getElementById('id-get').value;
  get(id, '/coach');
}

async function createCoach() {
  const costRate = document.getElementById('cost-rate-post').value;
  const password = document.getElementById('password-post').value;
  const email = document.getElementById('email-post').value;
  const firstName = document.getElementById('first-name-post').value;
  const secondName = document.getElementById('second-name-post').value;

  const createCoachDto = {
    costRate: costRate,
    password: password,
    email: email,
    firstName: firstName,
    secondName: secondName
  };

  create(createCoachDto, '/coach');
}

async function coachDelete() {
  const id = document.getElementById('id-delete').value;
  remove(id, '/coach');
}

async function updateCoach() {
  const id = document.getElementById('id-update').value;
  const costRate = document.getElementById('cost-rate-update').value;
  const password = document.getElementById('password-update').value;
  const email = document.getElementById('email-update').value;
  const firstName = document.getElementById('first-name-update').value;
  const secondName = document.getElementById('second-name-update').value;
  const inputs = document.querySelectorAll('.trainingInput');
  const trainings = Array.from(inputs).map(input => Number(input.value));
  
  const coachDto = {
    id: id,
    costRate: costRate,
    password: password,
    email: email,
    firstName: firstName,
    secondName: secondName,
    trainings: trainings
  };

  update(coachDto, '/coach');
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
      document.getElementById('responsePost').value = JSON.stringify(responseJson, null, 2);
    } else {
      console.error(`Error: ${response.status} ${response.statusText}`);
      document.getElementById('responsePost').value = `Error: ${response.status} ${response.statusText}`;
    }
  } catch (error) {
    console.error("Error: one of the ids does not exist.");
    document.getElementById('responsePost').value = "Error: one of the ids does not exist.";
  }
}