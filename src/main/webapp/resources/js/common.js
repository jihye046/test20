document.querySelector("#sidebarBtn").addEventListener('click', function(){
  openSidebar()
})

document.querySelector("#closeBtn").addEventListener('click', function(){
  closeSidebar()
})
const openSidebar = () => {
  document.querySelector("#sidebarMenu").style.width = "250px"
}
const closeSidebar = () => {
  document.querySelector("#sidebarMenu").style.width = "0px"
}


