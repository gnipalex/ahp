var fs = require('fs');
var _ = require('underscore');

module.exports = function(grunt) {    

    grunt.registerMultiTask('buildTemplates', 'Custom templating task', function() {
        
        function generateTemplate(filename) {
            var output = ''
            if (fs.lstatSync(filename).isDirectory()) {
                var filesInDirectory = fs.readdirSync(filename);
                filesInDirectory.forEach(function(item, index) {
                    output += generateTemplate(filename + '/' + item);
                });
            } else if (fs.lstatSync(filename).isFile()) {
                var fileContents = fs.readFileSync(filename, 'utf-8');
                var path = filename.substring(0, filename.lastIndexOf('.'));
                var compiledTemplate = _.template(fileContents, {
                    variable : 'it'
                }).source;
                output += 'window.app.templates["' + path + '"] = ('
                        + compiledTemplate + ');\n';
            }
            return output;
        }

        if (this.data.src && this.data.src.length) {
            grunt.log.writeln('Compiling templates ...');
            
            var output = '';
                    
            this.data.src.forEach(function(item) {
                output += generateTemplate(item);
            });

            fs.writeFileSync(this.data.dest, output);
            grunt.log.writeln('File ' + this.data.dest + ' created');
        } else {
            grunt.log.writeln('No templates are configured');
        }
        
    });
};