/* ckeditor */
let editor;
 
// 이미지 크기 조정 옵션 및 툴바 구성
const imageConfiguration = {
    resizeOptions: [
        {
            name: 'resizeImage:original',
            value: null,
            label: 'Original'
        },
        {
            name: 'resizeImage:custom',
            label: 'Custom',
            value: 'custom'
        },
        {
            name: 'resizeImage:40',
            value: '40',
            label: '40%'
        },
        {
            name: 'resizeImage:60',
            value: '60',
            label: '60%'
        }
    ],
    toolbar: ['resizeImage']
}; 
 
ClassicEditor
  .create(document.querySelector('#editor'), {
    // CKEditor configuration options
    extraPlugins: [MyCustomUploadAdapterPlugin],
    toolbar: [
      'heading', 
	  '|', 
	  'fontFamily', 
	  'fontSize', 
	  'bold', 
	  'italic', 
	  'fontColor', 
	  'fontBackgroundColor', 
	  'link', 
	  'bulletedList', 
	  'numberedList', 
	  '|', 
	  'outdent', 
	  'indent', 
	  '|', 
	  'imageUpload', 
	  'resizeImage',  // 여기 추가
	  'blockQuote', 
	  'insertTable', 
	  'mediaEmbed', 
	  'undo', 
	  'redo'
    ],
    language: 'ko',
    image: {
      toolbar: [
        'imageTextAlternative',
        'toggleImageCaption',
        'imageStyle:inline',
        'imageStyle:block',
        'imageStyle:side',
        ...imageConfiguration.toolbar
      ],
      resizeOptions: imageConfiguration.resizeOptions,
      resizeUnit: 'px'
    },
    table: {
      contentToolbar: [
        'tableColumn',
        'tableRow',
        'mergeTableCells'
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