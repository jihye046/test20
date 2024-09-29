let editor;
 
ClassicEditor
  .create(document.querySelector('#editor'), {
    // CKEditor configuration options
    extraPlugins: [MyCustomUploadAdapterPlugin],
    image: {
            toolbar: ['imageTextAlternative', 'imageStyle:full', 'imageStyle:side'],
            styles: [
                'alignLeft',
                'alignCenter',
                'alignRight',
                {
                    name: 'Responsive', // 사용자 정의 스타일
                    value: 'max-width: 60%; height: auto;'
                }
            ]
        }
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

