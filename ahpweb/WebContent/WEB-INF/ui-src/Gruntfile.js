module.exports = function(grunt) {

    // Project configuration.
    grunt.initConfig({
        myTemplate: {
            template : {
                src : ['templates'],
                dest : 'build/template.js'
            }
        },
        concat: {
            "options": { "separator": ";" },
            "build": {
                "src": [
                        "build/templates.js", 
                        "js/pages/*.js"
                ],
                "dest": "build/main.js"
            }
        }
    });

    // Load required modules
    grunt.loadNpmTasks('grunt-contrib-concat');
    //grunt.loadNpmTasks('grunt-contrib-template');
    
    // Task definitions
    grunt.registerTask('default', ['myTemplate', 'concat']);
    
    grunt.registerMultiTask('myTemplate', 'Custom templating task', function() {
        var fs = require('fs');
        var _ = require('underscore');
        
        function generateTemplate(filename) {
            grunt.log.writeln('generateTemplate: for filename ' + filename);
            
            var output = ''
            if (fs.lstatSync(filename).isDirectory()) {
                grunt.log.writeln('fs.lstatSync(' + filename + ').isDirectory() - yes'); 
                var filesInDirectory = fs.readdirSync(filename); 
                filesInDirectory.forEach( function(item, index) {
                    grunt.log.writeln('recursive call generateTemplate for directory ' + (filename + '/' + item));
                    output += generateTemplate(filename + '/' + item);
                });
            } else if (fs.lstatSync(filename).isFile()) {
                grunt.log.writeln('Compiling ' + filename);
                var fileContents = fs.readFileSync(filename, 'utf-8');
                var path = filename.substring(0, filename.lastIndexOf('.'));
                output += 'window.app.templates["' + path + '"] = ' +
                    '(' + _.template(fileContents, { variable : 'it' }).source + ');\n';
            }
            return output;
        }
        
        if (this.data.src && this.data.src.length) {
            grunt.log.writeln('Compiling templates ...');
            var output = 'window.app = window.app || {};\n' +
                         'window.app.templates = window.app.templates || {};';
            this.data.src.forEach(function(item, index) {
                output += generateTemplate(item);
            });
            fs.writeFileSync(this.data.dest, output);
            grunt.log.writeln('File ' + this.data.dest + ' created');
        } else {
            grunt.log.writeln('No templates are configured');
        }    
    });
    

};