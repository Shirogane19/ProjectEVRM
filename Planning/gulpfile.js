var gulp = require('gulp');
var sass = require('gulp-sass');
var browserSync = require('browser-sync').create();

//Synchronize the browser with the app folder
gulp.task('browserSync', function(){
   browserSync.init({
     server: {
       baseDir: 'WebContent'
     }
   })
});

gulp.task('sass', function(){
	  return gulp.src('WebContent/scss/*.scss')
	    .pipe(sass())
	    .pipe(gulp.dest('WebContent/css'))
	    .pipe(browserSync.reload({
	      stream: true
	  }))
});

// Actualize the scss, html and js files
gulp.task('watch', ['sass','browserSync'], function(){
   gulp.watch('WebContent/scss/*.scss', ['sass']);
   gulp.watch('WebContent/*.html', browserSync.reload);
   //gulp.watch('WebContent/js/*.js');
});
