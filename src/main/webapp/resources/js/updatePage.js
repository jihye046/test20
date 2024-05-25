let editor;
 
ClassicEditor
  .create(document.querySelector('#editor'), {
  	ckfinder: {
                    uploadUrl: '/board/imgUpload' // 서버의 이미지 업로드 URL로 설정
    }
  })
  .then( editor => {
    window.editor = editor
  })
  .catch( error => {
    console.error( error )
  })