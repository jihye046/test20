let editor;
 
ClassicEditor
  .create(document.querySelector('#editor'), {
    // CKEditor configuration options
  })
  .then( newEditor => {
    editor = newEditor
  })
  .catch( error => {
    console.error( error )
  })