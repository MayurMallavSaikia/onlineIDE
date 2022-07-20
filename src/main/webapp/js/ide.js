
function languageMode(lang){
    editor = ace.edit("editor");                                    // Language changing and theme changing logic
    editor.setTheme("ace/theme/monokai");
    editor.session.setMode("ace/mode/"+lang);
    editor.setShowPrintMargin(false);

    initCode(lang);                                                // sample code generator function call

    require('ace/ext/language_tools');
    
    editor.setOptions({                                            // AutoCode Generation menu 
        enableBasicAutocompletion: true,
        enableSnippets: true,
        enableLiveAutocompletion: true
    });
    
    fontChange(0);
}



function colorChange(){                                                     // color changing logic

    var mainBar=document.getElementById("mainBar");
    mainBar.style.background='#'+Math.floor(Math.random()*16777215).toString(16);
    mainBar.style.transition="all .2s linear";
    
    }




function executeCode(){

           let code = editor.getValue();
           //let jsonData = JSON.stringify(code);
           document.getElementById('codeText').value=code;

}

function fontChange(size){
	let fontsizeNum=0;
	fontSizeNum=18+parseInt(size);
	
	document.getElementById('editor').style.fontSize=fontSizeNum+'px';        // changing the font size of the editor 
   
    var popup = editor.completer.popup;         // changing the font size of autoCodeCompletion menu
    popup.container.style.fontSize=fontSizeNum+"px"; 
    popup.resize();

	
	
}


// sample code generator while choosing any programming Language
function initCode(lang){
    if(lang==="c_cpp")
       editor.setValue("#include <iostream>\n\nint main() {\n\tstd::cout << \"Hello, World!\";\n\treturn 0;\n}");

     else if(lang==="java"){
        editor.setValue("import java.io.*;\nimport java.util.*;\n\npublic class Main {\n\tpublic static void main(String[] args) {\n\t\tSystem.out.println(\"Hello, World!\");\n\t}\n}");
     }

     else if(lang==="python"){
        editor.setValue("print(\"Hello, World!\");");
     }
}


