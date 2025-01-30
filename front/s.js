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
    searchInput.classList.toggle('dark-mode');
}
// Fetch and Render Movies
function renderMovies(movies) {
    searchResults.innerHTML = ''; // پاک کردن نتایج قبلی

    movies.forEach(movie => {
        const movieCard = document.createElement('div');
        movieCard.classList.add('movie-card');

        movieCard.innerHTML = `
            <img src="${movie.image || 'default-movie-poster.jpg'}" alt="${movie.title}">
            <h3>${movie.title}</h3>
            <p>⭐ ${movie.rating || 'N/A'}</p>
            <p>${movie.year || 'Unknown Year'}</p>
            <p>${movie.genre ? movie.genre.join(', ') : 'No Genre Info'}</p>
        `;
        searchResults.appendChild(movieCard);
    });
}
// Handle Genre Clicks
genreItems.forEach(item => {
    item.addEventListener('click', () => {
        const genre = item.getAttribute('data-genre'); // گرفتن مقدار ژانر


        // ارسال درخواست به بک‌اند
        fetch(`http://localhost:8080/imdb/moviesByGender?genre=${genre}`) // آدرس صحیح را تنظیم کنید
            .then(response => response.json())
            .then(data => renderMovies(data)) // نمایش فیلم‌ها
            .catch(error => console.error('Error fetching movies by genre:', error));
    });
});
// Handle Movie Search
searchMovieButton.addEventListener('click', () => {
    const query = searchInput.value.trim();
    if (!query) {
        alert('Please enter a movie name!');
        return;
    }

    // ارسال درخواست جستجوی فیلم
    fetch(`http://localhost:8080/imdb/movies/${encodeURIComponent(query)}`)
        .then(response => {
            if (!response.ok) throw new Error('Movie not found');
            return response.json();
        })
        .then(data => renderMovies([data])) // تنها یک فیلم نمایش داده می‌شود
        .catch(error => console.error('Error searching for movie:', error));
});
// Handle Actor Search
searchActorButton.addEventListener('click', () => {
    const query = searchInput.value.trim();
    if (!query) {
        alert('Please enter an actor name!');
        return;
    }

    // ارسال درخواست جستجوی بازیگر
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
// Call fetchMovies when page loads (optional for initial display)
document.addEventListener('DOMContentLoaded', () => {
    fetch('http://localhost:8080/imdb/moviesByGender') // مسیر پیش‌فرض برای گرفتن فیلم‌ها
        .then(response => response.json())
        .then(data => renderMovies(data))
        .catch(error => console.error('Error fetching initial movies:', error));
});

