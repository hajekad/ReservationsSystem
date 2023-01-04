async function traineeGet() {
    const id = document.getElementById('id-input-get').value;
    try {
      const response = await fetch(`http://localhost:6060/trainee?id=${id}`);
      if (response.ok) {
        const trainee = await response.json();
        console.log("Trainee:\n" + JSON.stringify(trainee, null, 2));
        document.getElementById('responseGet').value = JSON.stringify(trainee, null, 2);
      } else {
        console.error(`Error: ${response.status} ${response.statusText}`);
        document.getElementById('responseGet').value = `Error: ${response.status} ${response.statusText}`;
      }
    } catch (error) {
      console.error(error);
      document.getElementById('responseGet').value = error.toString();
    }
  }
  
  async function createTrainee() {    
    const username = document.getElementById('cost-rate-input-post').value;
    const password = document.getElementById('cost-rate-input-post').value;
    const email = document.getElementById('cost-rate-input-post').value;
    const firstName = document.getElementById('cost-rate-input-post').value;
    const secondName = document.getElementById('cost-rate-input-post').value;
    const skillCap = document.getElementById('cost-rate-input-post').value;

    const createTraineeDto = {
        username: username,
        password: password,
        email: email,
        firstName: firstName,
        secondName: secondName,
        skillCap: skillCap,
    };
  
    try {
      const response = await fetch('http://localhost:6060/trainee', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(createTraineeDto)
      });
      if (response.ok) {
        const trainee = await response.json();
        console.log("Trainee:\n" + JSON.stringify(trainee, null, 2));
        document.getElementById('responsePost').value = JSON.stringify(trainee, null, 2);
      } else {
        console.error(`Error: ${response.status} ${response.statusText}`);
        document.getElementById('responsePost').value = `Error: ${response.status} ${response.statusText}`;
      }
    } catch (error) {
      console.error(error);
      document.getElementById('responsePost').value = error.toString();
    }
  }
  
  async function traineeDelete() {
    const id = document.getElementById('id-input-delete').value;
    try {
      const response = await fetch(`http://localhost:6060/trainee?id=${id}`, {
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
  
  async function updateTrainee() {
    const id = document.getElementById('id-update').value;
    const username = document.getElementById('id-update').value;
    const password = document.getElementById('id-update').value;
    const email = document.getElementById('id-update').value;
    const firstName = document.getElementById('id-update').value;
    const secondName = document.getElementById('id-update').value;
    const skillCap = document.getElementById('id-update').value;
    const trainings = document.getElementById('id-update').value;
    
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
  
    console.log("Sending: " + JSON.stringify(traineeDto));
  
    try {
      const response = await fetch('http://localhost:6060/trainee', {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(traineeDto)
      });
      if (response.ok) {
        const trainee = await response.json();
        console.log("Trainee:\n" + JSON.stringify(trainee, null, 2));
        document.getElementById('responsePost').value = JSON.stringify(trainee, null, 2);
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
  