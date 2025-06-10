/* ckeditor */
let editor;
 
 // 글 작성
ClassicEditor
  .create(document.querySelector('#editor'), {
    // CKEditor configuration options
    extraPlugins: [MyCustomUploadAdapterPlugin]
  })
  .then( newEditor => {
    editor = newEditor
  })
  .catch( error => {
    console.error( error )
  })

function MyCustomUploadAdapterPlugin(editor) {
  editor.plugins.get('FileRepository').createUploadAdapter = (loader) => {
      return new UploadAdapter(loader)
  }
}

// 검색한 주소를 [input]에 set
const searchedAdd = () => {
    new daum.Postcode({
        oncomplete: function(data) {
          const addr = data.address // 최종 주소 변수
          document.querySelector('#inputAdd').value = addr
        }
    }).open()
}