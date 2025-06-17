/* ckeditor */
let editor;
 
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

/* 검색한 주소를 [input]에 set
================================================== */
const searchedAdd = () => {
    new daum.Postcode({
        oncomplete: function(data) {
          const addr = data.address // 최종 주소 변수
          document.querySelector('#inputAdd').value = addr
        }
    }).open()
}

/* 태그 입력창
================================================== */
const tagInput = document.querySelector("#tagInput") // 실제 태그 입력창
const tagify = new Tagify(tagInput, {
  // 드롭다운 자동완성
  dropdown: {
    enabled: 1,
  },
  // ghost-text 비활성화
  autoComplete: {
    enabled: false
  },
  // 자동완성 목록
  whitelist: ["a", "aa", "b", "bb", "ccc"]
})

const tagJsonStr = document.querySelector("#tagJsonList").getAttribute("data-tagJsonList")
const tagList = JSON.parse(tagJsonStr)
tagify.addTags(tagList)