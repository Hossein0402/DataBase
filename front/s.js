// DOM Elements  
const themeSwitch = document.getElementById('themeSwitch');  
const searchInput = document.getElementById('searchInput');  
const searchMovieButton = document.getElementById('searchMovieButton');  
const searchActorButton = document.getElementById('searchActorButton');  
const genreItems = document.querySelectorAll('#genreList li');  
const searchResults = document.getElementById('searchResults');  

// Event Listeners  
themeSwitch.addEventListener('click', toggleTheme);  

// Function to toggle dark/light theme  
function toggleTheme() {  
    document.body.classList.toggle('dark-mode');  
    searchInput.classList.toggle('dark-mode'); // Updated to target input directly  
    genreItems.forEach(item => item.classList.toggle('dark-mode')); // Optional: add dark mode to genre items  
}  

// For demonstration, you can add more interactivity here  
console.log('JavaScript is connected and running properly!');  

// Fetch and Render Movies  
function fetchMovies() {  
    fetch('movies.json')  
        .then(response => response.json())  
        .then(data => renderMovies(data))  
        .catch(error => console.error('Error fetching movies:', error));  
}  

// Render Movies on Cards  
function renderMovies(movies) {  
    searchResults.innerHTML = ''; // Clear previous results  
    movies.forEach(movie => {  
        const movieCard = document.createElement('div');  
        movieCard.classList.add('movie-card');  
        movieCard.innerHTML = `  
            <img src="${movie.image}" alt="${movie.title}">  
            <h3>${movie.title}</h3>  
            <p>⭐ ${movie.rating}</p>  
            <p>${movie.year}</p>  
            <p>${movie.genre}</p> <!-- Fixed to correctly display genre -->  
        `;  
        searchResults.appendChild(movieCard);  
    });  
}  

// Call fetchMovies when page loads  
document.addEventListener('DOMContentLoaded', fetchMovies);  

// Add click event to each genre list item  
genreItems.forEach(item => {  
    item.addEventListener('click', () => {  
        const genre = item.getAttribute('data-genre');  
        fetch(`http://localhost:8080/imdb/moviesByGender/${encodeURIComponent(genre)}`) // آدرس صحیح را تنظیم کنید
            .then(response => response.json())
            .then(data => renderMovies(data)) // نمایش فیلم‌ها
            .catch(error => console.error('Error fetching movies by genre:', error));
    }); 
});  

// Search for Movies  
searchMovieButton.addEventListener('click', () => {  
    const query = searchInput.value.trim();  
    if (!query) {  
        alert('Please enter a movie name!');  
        return;  
    }  
    // Adjusted to use backticks for template literals  
    fetch(`http://localhost:8080/imdb/movies/${encodeURIComponent(query)}`)  
    .then(response => {  
        if (!response.ok) throw new Error('Movie not found');  
        return response.json();  
    })  
    .then(data => {  
        console.log('Movie data:', data); // Log the received movie data  
        renderMovies([data]); // Only one movie is displayed  
    })  
        .catch(error => console.error('Error searching for movie:', error));
    });

// Search for Actors  
searchActorButton.addEventListener('click', () => {  
    const query = searchInput.value.trim();  
    if (!query) {  
        alert('Please enter an actor name!');  
        return;  
    }  
    fetch(`http://localhost:8080/imdb/actors/${encodeURIComponent(query)}`)
        .then(response => {
            if (!response.ok) throw new Error('Actor not found');
            return response.json();
        })
        .then(data => {
            // برای نمایش فیلم‌های بازیگر، فرض می‌کنیم بک‌اند لیستی از فیلم‌های بازیگر را برمی‌گرداند
            if (data.movies) {
                renderMovies(data.movies);
            } else {
                alert('No movies found for this actor!');
            }
        })
        .catch(error => console.error('Error searching for actor:', error));
});