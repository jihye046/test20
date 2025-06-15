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

/* 태그 입력창
================================================== */
const tagInput = document.querySelector("#tagInput") // 실제 태그 입력창
const tagValue = document.querySelector("#tagValue") // 서버로 전송할 태그

document.querySelector('form').addEventListener('submit', () => {
  const tagData = tagInput.value
  tagValue.value = JSON.stringify(tagData)
  console.log(tagValue.value) 
})

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

// tagify.on('change', (e) => {
//   console.log(Array.isArray(e.detail.value))
// })
tagify.addTags(["a"])
