// Navigation
document.querySelectorAll('.nav-list li').forEach(item => {
    item.addEventListener('click', () => {
        const tool = item.getAttribute('data-tool');
        
        // Update active nav item
        document.querySelectorAll('.nav-list li').forEach(li => li.classList.remove('active'));
        item.classList.add('active');
        
        // Show corresponding tool section
        document.querySelectorAll('.tool-section').forEach(section => section.classList.remove('active'));
        document.getElementById(tool).classList.add('active');
    });
});

// 1. TO-DO LIST
let todos = [];

function addTodo() {
    const input = document.getElementById('todo-input');
    const text = input.value.trim();
    
    if (text === '') return;
    
    todos.push({ id: Date.now(), text, completed: false });
    input.value = '';
    renderTodos();
}

function toggleTodo(id) {
    todos = todos.map(todo => 
        todo.id === id ? { ...todo, completed: !todo.completed } : todo
    );
    renderTodos();
}

function deleteTodo(id) {
    todos = todos.filter(todo => todo.id !== id);
    renderTodos();
}

function renderTodos() {
    const list = document.getElementById('todo-list');
    const empty = document.getElementById('todo-empty');
    
    if (todos.length === 0) {
        list.innerHTML = '';
        empty.style.display = 'block';
        return;
    }
    
    empty.style.display = 'none';
    list.innerHTML = todos.map(todo => `
        <li class="todo-item ${todo.completed ? 'completed' : ''}">
            <input type="checkbox" ${todo.completed ? 'checked' : ''} 
                   onchange="toggleTodo(${todo.id})">
            <span>${todo.text}</span>
            <button onclick="deleteTodo(${todo.id})">Delete</button>
        </li>
    `).join('');
}

// 2. TEMPERATURE CONVERTER
function convertTemp(from) {
    const celsius = document.getElementById('celsius');
    const fahrenheit = document.getElementById('fahrenheit');
    const kelvin = document.getElementById('kelvin');
    
    let c, f, k;
    
    if (from === 'c') {
        c = parseFloat(celsius.value) || 0;
        f = (c * 9/5) + 32;
        k = c + 273.15;
    } else if (from === 'f') {
        f = parseFloat(fahrenheit.value) || 0;
        c = (f - 32) * 5/9;
        k = c + 273.15;
    } else {
        k = parseFloat(kelvin.value) || 0;
        c = k - 273.15;
        f = (c * 9/5) + 32;
    }
    
    if (from !== 'c') celsius.value = c.toFixed(2);
    if (from !== 'f') fahrenheit.value = f.toFixed(2);
    if (from !== 'k') kelvin.value = k.toFixed(2);
}

// 3. PASSWORD GENERATOR
function generatePassword() {
    const length = parseInt(document.getElementById('pass-length').value) || 16;
    const upper = document.getElementById('pass-upper').checked;
    const lower = document.getElementById('pass-lower').checked;
    const numbers = document.getElementById('pass-numbers').checked;
    const symbols = document.getElementById('pass-symbols').checked;
    
    let chars = '';
    if (upper) chars += 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';
    if (lower) chars += 'abcdefghijklmnopqrstuvwxyz';
    if (numbers) chars += '0123456789';
    if (symbols) chars += '!@#$%^&*()_+-=[]{}|;:,.<>?';
    
    if (chars === '') {
        document.getElementById('password-output').textContent = 'Please select at least one option';
        return;
    }
    
    let password = '';
    for (let i = 0; i < length; i++) {
        password += chars.charAt(Math.floor(Math.random() * chars.length));
    }
    
    document.getElementById('password-output').textContent = password;
}

// 4. CALCULATOR
let calcDisplay = '0';
let calcHistory = [];

function appendCalc(value) {
    if (calcDisplay === '0') {
        calcDisplay = value;
    } else {
        calcDisplay += value;
    }
    document.getElementById('calc-display').textContent = calcDisplay;
}

function clearCalc() {
    calcDisplay = '0';
    document.getElementById('calc-display').textContent = calcDisplay;
}

function deleteCalc() {
    calcDisplay = calcDisplay.slice(0, -1) || '0';
    document.getElementById('calc-display').textContent = calcDisplay;
}

function calculateCalc() {
    try {
        const result = eval(calcDisplay);
        const history = `${calcDisplay} = ${result}`;
        calcHistory.unshift(history);
        if (calcHistory.length > 10) calcHistory.pop();
        
        calcDisplay = result.toString();
        document.getElementById('calc-display').textContent = calcDisplay;
        
        const historyList = document.getElementById('calc-history-list');
        historyList.innerHTML = calcHistory.map(h => `<li>${h}</li>`).join('');
    } catch (e) {
        document.getElementById('calc-display').textContent = 'Error';
        calcDisplay = '0';
    }
}

// 5. FILE WORD COUNTER
document.getElementById('word-text').addEventListener('input', countWords);

function countWords() {
    const text = document.getElementById('word-text').value;
    const words = text.trim().split(/\s+/).filter(w => w.length > 0).length;
    const chars = text.length;
    const lines = text.split('\n').length;
    
    document.getElementById('word-count').textContent = words;
    document.getElementById('char-count').textContent = chars;
    document.getElementById('line-count').textContent = lines;
}

function loadFile() {
    const file = document.getElementById('word-file').files[0];
    if (file) {
        const reader = new FileReader();
        reader.onload = function(e) {
            document.getElementById('word-text').value = e.target.result;
            countWords();
        };
        reader.readAsText(file);
    }
}

// 6. URL SLUG GENERATOR
function generateSlug() {
    const input = document.getElementById('slug-input').value;
    const slug = input
        .toLowerCase()
        .trim()
        .replace(/[^a-z0-9\s-]/g, '')
        .replace(/\s+/g, '-')
        .replace(/-+/g, '-');
    
    document.getElementById('slug-output').textContent = slug || 'Enter text to generate slug';
}

// 7. QUOTE GENERATOR
const quotes = [
    { text: "The only way to do great work is to love what you do.", author: "Steve Jobs" },
    { text: "Innovation distinguishes between a leader and a follower.", author: "Steve Jobs" },
    { text: "Life is what happens when you're busy making other plans.", author: "John Lennon" },
    { text: "The future belongs to those who believe in the beauty of their dreams.", author: "Eleanor Roosevelt" },
    { text: "It is during our darkest moments that we must focus to see the light.", author: "Aristotle" },
    { text: "Be yourself; everyone else is already taken.", author: "Oscar Wilde" },
    { text: "The only impossible journey is the one you never begin.", author: "Tony Robbins" },
    { text: "In the middle of difficulty lies opportunity.", author: "Albert Einstein" },
    { text: "Success is not final, failure is not fatal: it is the courage to continue that counts.", author: "Winston Churchill" },
    { text: "Believe you can and you're halfway there.", author: "Theodore Roosevelt" }
];

function generateQuote() {
    const quote = quotes[Math.floor(Math.random() * quotes.length)];
    document.getElementById('quote-text').textContent = `"${quote.text}"`;
    document.getElementById('quote-author').textContent = `— ${quote.author}`;
}

// 8. PALINDROME CHECKER
function checkPalindrome() {
    const input = document.getElementById('palindrome-input').value;
    const cleaned = input.toLowerCase().replace(/[^a-z0-9]/g, '');
    const reversed = cleaned.split('').reverse().join('');
    const isPalindrome = cleaned === reversed && cleaned.length > 0;
    
    const result = document.getElementById('palindrome-result');
    if (input.trim() === '') {
        result.textContent = 'Please enter some text';
        result.style.color = '#666';
    } else if (isPalindrome) {
        result.textContent = `"${input}" is a palindrome! ✓`;
        result.style.color = '#28a745';
    } else {
        result.textContent = `"${input}" is not a palindrome.`;
        result.style.color = '#dc3545';
    }
}

// 9. PRIME NUMBER CHECKER
function checkPrime() {
    const num = parseInt(document.getElementById('prime-input').value);
    const result = document.getElementById('prime-result');
    
    if (isNaN(num) || num < 2) {
        result.textContent = 'Please enter a number greater than 1';
        result.style.color = '#666';
        return;
    }
    
    const isPrime = isPrimeNumber(num);
    
    if (isPrime) {
        result.textContent = `${num} is a prime number! ✓`;
        result.style.color = '#28a745';
    } else {
        result.textContent = `${num} is not a prime number.`;
        result.style.color = '#dc3545';
    }
}

function isPrimeNumber(n) {
    if (n <= 1) return false;
    if (n <= 3) return true;
    if (n % 2 === 0 || n % 3 === 0) return false;
    for (let i = 5; i * i <= n; i += 6) {
        if (n % i === 0 || n % (i + 2) === 0) return false;
    }
    return true;
}

// 10. INTEREST CALCULATOR
function calculateInterest() {
    const principal = parseFloat(document.getElementById('principal').value) || 0;
    const rate = parseFloat(document.getElementById('rate').value) || 0;
    const time = parseFloat(document.getElementById('time').value) || 0;
    const type = document.getElementById('interest-type').value;
    
    let interest, total;
    
    if (type === 'simple') {
        interest = (principal * rate * time) / 100;
        total = principal + interest;
    } else {
        total = principal * Math.pow(1 + rate / 100, time);
        interest = total - principal;
    }
    
    const result = document.getElementById('interest-result');
    result.innerHTML = `
        <strong>Principal:</strong> $${principal.toFixed(2)}<br>
        <strong>Interest Earned:</strong> $${interest.toFixed(2)}<br>
        <strong>Total Amount:</strong> $${total.toFixed(2)}<br>
        <strong>Type:</strong> ${type === 'simple' ? 'Simple Interest' : 'Compound Interest'}
    `;
}

// 11. BMI CALCULATOR
function calculateBMI() {
    const weight = parseFloat(document.getElementById('weight').value);
    const height = parseFloat(document.getElementById('height').value) / 100; // cm to m
    
    if (!weight || !height) {
        document.getElementById('bmi-result').textContent = 'Please enter valid values';
        return;
    }
    
    const bmi = weight / (height * height);
    let category = '';
    
    if (bmi < 18.5) category = 'Underweight';
    else if (bmi < 25) category = 'Normal weight';
    else if (bmi < 30) category = 'Overweight';
    else category = 'Obese';
    
    document.getElementById('bmi-result').innerHTML = `
        <strong>Your BMI:</strong> ${bmi.toFixed(1)}<br>
        <strong>Category:</strong> ${category}
    `;
}

// 12. LIVE CLOCK
function updateClock() {
    const now = new Date();
    const time = now.toLocaleTimeString('en-US', { hour12: false });
    const date = now.toLocaleDateString('en-US', { 
        weekday: 'long', 
        year: 'numeric', 
        month: 'long', 
        day: 'numeric' 
    });
    
    document.getElementById('clock-time').textContent = time;
    document.getElementById('clock-date').textContent = date;
}

setInterval(updateClock, 1000);
updateClock();

// 13. SUDOKU GAME
let sudokuGrid = [];
let sudokuSolution = [];

function newSudoku() {
    // Generate a simple Sudoku puzzle
    sudokuSolution = generateSudokuSolution();
    sudokuGrid = JSON.parse(JSON.stringify(sudokuSolution));
    
    // Remove numbers to create puzzle (40-50 cells)
    const cellsToRemove = 45;
    for (let i = 0; i < cellsToRemove; i++) {
        const row = Math.floor(Math.random() * 9);
        const col = Math.floor(Math.random() * 9);
        sudokuGrid[row][col] = 0;
    }
    
    renderSudoku();
    document.getElementById('sudoku-result').textContent = '';
}

function generateSudokuSolution() {
    const grid = Array(9).fill().map(() => Array(9).fill(0));
    
    // Fill diagonal 3x3 boxes
    for (let box = 0; box < 9; box += 3) {
        fillBox(grid, box, box);
    }
    
    // Fill remaining cells
    solveSudoku(grid);
    return grid;
}

function fillBox(grid, row, col) {
    const nums = [1, 2, 3, 4, 5, 6, 7, 8, 9];
    shuffleArray(nums);
    
    let idx = 0;
    for (let i = 0; i < 3; i++) {
        for (let j = 0; j < 3; j++) {
            grid[row + i][col + j] = nums[idx++];
        }
    }
}

function shuffleArray(array) {
    for (let i = array.length - 1; i > 0; i--) {
        const j = Math.floor(Math.random() * (i + 1));
        [array[i], array[j]] = [array[j], array[i]];
    }
}

function solveSudoku(grid) {
    for (let row = 0; row < 9; row++) {
        for (let col = 0; col < 9; col++) {
            if (grid[row][col] === 0) {
                for (let num = 1; num <= 9; num++) {
                    if (isValidSudoku(grid, row, col, num)) {
                        grid[row][col] = num;
                        if (solveSudoku(grid)) return true;
                        grid[row][col] = 0;
                    }
                }
                return false;
            }
        }
    }
    return true;
}

function isValidSudoku(grid, row, col, num) {
    for (let i = 0; i < 9; i++) {
        if (grid[row][i] === num || grid[i][col] === num) return false;
    }
    
    const boxRow = Math.floor(row / 3) * 3;
    const boxCol = Math.floor(col / 3) * 3;
    for (let i = 0; i < 3; i++) {
        for (let j = 0; j < 3; j++) {
            if (grid[boxRow + i][boxCol + j] === num) return false;
        }
    }
    
    return true;
}

function renderSudoku() {
    const container = document.getElementById('sudoku-grid');
    container.innerHTML = '';
    
    for (let i = 0; i < 9; i++) {
        for (let j = 0; j < 9; j++) {
            const cell = document.createElement('div');
            cell.className = 'sudoku-cell';
            
            const input = document.createElement('input');
            input.type = 'number';
            input.min = 1;
            input.max = 9;
            input.id = `cell-${i}-${j}`;
            
            if (sudokuGrid[i][j] !== 0) {
                input.value = sudokuGrid[i][j];
                cell.classList.add('fixed');
                input.disabled = true;
            }
            
            cell.appendChild(input);
            container.appendChild(cell);
        }
    }
}

function checkSudoku() {
    let isCorrect = true;
    
    for (let i = 0; i < 9; i++) {
        for (let j = 0; j < 9; j++) {
            const input = document.getElementById(`cell-${i}-${j}`);
            const value = parseInt(input.value) || 0;
            
            if (value !== sudokuSolution[i][j]) {
                isCorrect = false;
                break;
            }
        }
        if (!isCorrect) break;
    }
    
    const result = document.getElementById('sudoku-result');
    if (isCorrect) {
        result.textContent = 'Congratulations! You solved it! 🎉';
        result.style.color = '#28a745';
    } else {
        result.textContent = 'Not quite right. Keep trying!';
        result.style.color = '#dc3545';
    }
}

// Initialize Sudoku
newSudoku();

// 14. SIMPLE CHATBOT
function sendMessage() {
    const input = document.getElementById('chat-input');
    const message = input.value.trim();
    
    if (message === '') return;
    
    const chatBox = document.getElementById('chat-box');
    
    // Add user message
    const userMsg = document.createElement('div');
    userMsg.className = 'chat-message user';
    userMsg.textContent = message;
    chatBox.appendChild(userMsg);
    
    input.value = '';
    
    // Generate bot response
    setTimeout(() => {
        const botMsg = document.createElement('div');
        botMsg.className = 'chat-message bot';
        botMsg.textContent = getBotResponse(message.toLowerCase());
        chatBox.appendChild(botMsg);
        chatBox.scrollTop = chatBox.scrollHeight;
    }, 500);
    
    chatBox.scrollTop = chatBox.scrollHeight;
}

function getBotResponse(message) {
    if (message.includes('hello') || message.includes('hi')) {
        return 'Hello! How can I help you today?';
    } else if (message.includes('how are you')) {
        return "I'm doing great, thank you for asking! How about you?";
    } else if (message.includes('name')) {
        return "I'm a simple chatbot created for Java Tools!";
    } else if (message.includes('time')) {
        return `The current time is ${new Date().toLocaleTimeString()}`;
    } else if (message.includes('date')) {
        return `Today is ${new Date().toLocaleDateString()}`;
    } else if (message.includes('help')) {
        return 'I can chat with you about basic topics. Try asking about the time, date, or just say hello!';
    } else if (message.includes('bye')) {
        return 'Goodbye! Have a great day!';
    } else {
        const responses = [
            'That\'s interesting! Tell me more.',
            'I see. What else would you like to know?',
            'Interesting question! I\'m still learning.',
            'I\'m not sure about that, but I\'m here to chat!',
            'Can you elaborate on that?'
        ];
        return responses[Math.floor(Math.random() * responses.length)];
    }
}

// 15. TIC TAC TOE
let tttBoard = ['', '', '', '', '', '', '', '', ''];
let tttCurrentPlayer = 'X';
let tttGameActive = true;

function initTTT() {
    const grid = document.getElementById('ttt-grid');
    grid.innerHTML = '';
    
    for (let i = 0; i < 9; i++) {
        const cell = document.createElement('div');
        cell.className = 'ttt-cell';
        cell.onclick = () => handleTTTClick(i);
        grid.appendChild(cell);
    }
}

function handleTTTClick(index) {
    if (!tttGameActive || tttBoard[index] !== '') return;
    
    tttBoard[index] = tttCurrentPlayer;
    renderTTT();
    
    if (checkTTTWinner()) {
        document.getElementById('ttt-result').textContent = `Player ${tttCurrentPlayer} wins! 🎉`;
        document.getElementById('ttt-result').style.color = '#28a745';
        tttGameActive = false;
        return;
    }
    
    if (!tttBoard.includes('')) {
        document.getElementById('ttt-result').textContent = "It's a draw!";
        document.getElementById('ttt-result').style.color = '#666';
        tttGameActive = false;
        return;
    }
    
    tttCurrentPlayer = tttCurrentPlayer === 'X' ? 'O' : 'X';
    document.getElementById('ttt-player').textContent = `Player ${tttCurrentPlayer}'s Turn`;
}

function renderTTT() {
    const cells = document.querySelectorAll('.ttt-cell');
    cells.forEach((cell, index) => {
        cell.textContent = tttBoard[index];
        cell.className = 'ttt-cell';
        if (tttBoard[index] !== '') {
            cell.classList.add('taken');
            cell.classList.add(tttBoard[index].toLowerCase());
        }
    });
}

function checkTTTWinner() {
    const winPatterns = [
        [0, 1, 2], [3, 4, 5], [6, 7, 8], // Rows
        [0, 3, 6], [1, 4, 7], [2, 5, 8], // Columns
        [0, 4, 8], [2, 4, 6] // Diagonals
    ];
    
    return winPatterns.some(pattern => {
        const [a, b, c] = pattern;
        return tttBoard[a] !== '' && 
               tttBoard[a] === tttBoard[b] && 
               tttBoard[a] === tttBoard[c];
    });
}

function resetTTT() {
    tttBoard = ['', '', '', '', '', '', '', '', ''];
    tttCurrentPlayer = 'X';
    tttGameActive = true;
    document.getElementById('ttt-player').textContent = "Player X's Turn";
    document.getElementById('ttt-result').textContent = '';
    renderTTT();
}

// Initialize Tic Tac Toe
initTTT();