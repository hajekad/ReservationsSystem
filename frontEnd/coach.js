async function coachGetRequest() {
    const id = document.getElementById('id-input').value;
    const response = await fetch(`http://localhost:6060/coach?id=${id}`);
    const coach = await response.json();
    console.log("Coach:\n" + JSON.stringify(coach, null, 2));
    document.getElementById('responseGet').value = JSON.stringify(coach, null, 2);
}

async function createCoach() {
    const costRate = document.getElementById('cost-rate-input').value;
    const password = document.getElementById('password-input').value;
    const email = document.getElementById('email-input').value;
    const firstName = document.getElementById('first-name-input').value;
    const secondName = document.getElementById('second-name-input').value;

    const createCoachDto = {
      costRate: costRate,
      password: password,
      email: email,
      firstName: firstName,
      secondName: secondName
    };

    const response = await fetch('http://localhost:6060/coach', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(createCoachDto)
    });
    const coach = await response.json();
    console.log("Coach:\n" + JSON.stringify(coach, null, 2));
    document.getElementById('responsePost').value = JSON.stringify(coach, null, 2);
}