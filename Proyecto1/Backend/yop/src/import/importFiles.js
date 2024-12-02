export function getStoredFiles() {
  return JSON.parse(localStorage.getItem("uploadedFiles")) || {};
}

export function getSubtitle() {
  return localStorage.getItem("subtitle");
}

export function saveFiles(files) {
  localStorage.setItem("uploadedFiles", JSON.stringify(files));
}

export function saveSubtitle(subtitle) {
  localStorage.setItem("subtitle", subtitle);
}