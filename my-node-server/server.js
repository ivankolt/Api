const express = require('express');
const bodyParser = require('body-parser');
const cors = require('cors');

const app = express();
const PORT = 3000;

// Middleware
app.use(cors());
app.use(bodyParser.json());


let users = [
    { id: 1, name: 'Иван Кольцов', age: 18 },
    { id: 2, name: 'Вадим Ахлестин', age: -18 },
];


app.get('/users', (req, res) => {
    res.json(users);
});

app.get('/users', (req, res) => {
    console.log('GET /users');
    res.json(users);
});



app.post('/users', (req, res) => {
    const newUser = req.body;
    newUser.id = users.length + 1; 
    users.push(newUser);
    res.status(201).json(newUser);
});

app.listen(PORT, () => {
    console.log(`Server is running on http://localhost:${PORT}`);
});