module.exports = function(grunt) {

    // Load required modules
    grunt.loadNpmTasks('grunt-contrib-concat');
    grunt.loadNpmTasks('grunt-mkdir');
    grunt.loadNpmTasks('grunt-contrib-copy');
    
    grunt.loadTasks('tasks');

    // Project configuration.
    grunt.initConfig({
        mkdir : {
            all : {
                options : {
                    create : [ 'build' ]
                },
            },
        },
        buildTemplates : {
            template : {
                src : [ 'templates' ],
                dest : 'build/template.js'
            }
        },
        concat : {
            "options" : {
                "separator" : ";"
            },
            "build" : {
                "src" : [
                    "js/namespaces.js",
                    "build/template.js", 
                    "js/pages/*.js", 
                    "js/components/*.js",
                    "js/router.js" 
                 ],
                "dest" : "build/main.js"
            }
        },
        copy: {
          main: {
            flatten: true,
            expand: true,
            src: 'build/main.js',
            dest: '../../static/js/'
          }
        }
    });

    // Task definitions
    grunt.registerTask('default', [ 'mkdir', 'buildTemplates', 'concat', 'copy' ]);

};