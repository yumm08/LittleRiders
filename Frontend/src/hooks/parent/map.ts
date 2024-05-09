import { useEffect, useState } from 'react'

export function useSetParentMap() {
  const options = {
    center: new naver.maps.LatLng(37.359924641705476, 127.1148204803467),
    zoom: 13,
    minZoom: 7,
    zoomControl: false,
    disableKineticPOan: false,
  }
  const [naverMap, setNaverMap] = useState<naver.maps.Map>()

  useEffect(() => {
    const map = new naver.maps.Map('parentMap', options)
    setNaverMap(map)
  }, [])
  return { naverMap }
}
