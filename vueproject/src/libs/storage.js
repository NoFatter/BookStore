
/**
 * 存储localStorage
 */
export const setStore = (name, content) => {
  if (!name) return
  if (typeof content !== 'string') {
    content = JSON.stringify(content)
  }
  window.localStorage.setItem(`${name.toUpperCase()}`, content)
}

/**
 * 获取localStorage
 */
export const getStore = name => {
  if (!name) return
  return window.localStorage.getItem(`${name.toUpperCase()}`)
}

/**
 * 删除localStorage
 */
export const removeStore = name => {
  if (!name) return
  window.localStorage.removeItem(`${name.toUpperCase()}`)
}
