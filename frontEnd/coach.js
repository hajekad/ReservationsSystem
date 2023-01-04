async function coachGet() {
  const id = document.getElementById('id-input-get').value;
  try {
    const response = await fetch(`http://localhost:6060/coach?id=${id}`);
    if (response.ok) {
      const coach = await response.json();
      console.log("Coach:\n" + JSON.stringify(coach, null, 2));
      document.getElementById('responseGet').value = JSON.stringify(coach, null, 2);
    } else {
      console.error(`Error: ${response.status} ${response.statusText}`);
      document.getElementById('responseGet').value = `Error: ${response.status} ${response.statusText}`;
    }
  } catch (error) {
    console.error(error);
    document.getElementById('responseGet').value = error.toString();
  }
}

async function createCoach() {
  const costRate = document.getElementById('cost-rate-input-post').value;
  const password = document.getElementById('password-input-post').value;
  const email = document.getElementById('email-input-post').value;
  const firstName = document.getElementById('first-name-input-post').value;
  const secondName = document.getElementById('second-name-input-post').value;

  const createCoachDto = {
    costRate: costRate,
    password: password,
    email: email,
    firstName: firstName,
    secondName: secondName
  };

  try {
    const response = await fetch('http://localhost:6060/coach', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(createCoachDto)
    });
    if (response.ok) {
      const coach = await response.json();
      console.log("Coach:\n" + JSON.stringify(coach, null, 2));
      document.getElementById('responsePost').value = JSON.stringify(coach, null, 2);
    } else {
      console.error(`Error: ${response.status} ${response.statusText}`);
      document.getElementById('responsePost').value = `Error: ${response.status} ${response.statusText}`;
    }
  } catch (error) {
    console.error(error);
    document.getElementById('responsePost').value = error.toString();
  }
}

async function coachDelete() {
  const id = document.getElementById('id-input-delete').value;
  try {
    const response = await fetch(`http://localhost:6060/coach?id=${id}`, {
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

async function updateCoach() {
  const id = document.getElementById('id-update').value;
  const costRate = document.getElementById('cost-rate-input-update').value;
  const password = document.getElementById('password-input-update').value;
  const email = document.getElementById('email-input-update').value;
  const firstName = document.getElementById('first-name-input-update').value;
  const secondName = document.getElementById('second-name-input-update').value;
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

  console.log("Sending: " + JSON.stringify(coachDto));

  try {
    const response = await fetch('http://localhost:6060/coach', {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(coachDto)
    });
    if (response.ok) {
      const coach = await response.json();
      console.log("Coach:\n" + JSON.stringify(coach, null, 2));
      document.getElementById('responsePost').value = JSON.stringify(coach, null, 2);
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
