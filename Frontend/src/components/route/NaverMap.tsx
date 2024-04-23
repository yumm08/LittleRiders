import React, { useEffect, useRef } from 'react'

import { BASE_LAT, BASE_LNG } from '@constants/map'

const options = {
  center: new naver.maps.LatLng(BASE_LAT, BASE_LNG),
  zoom: 13,
  minZoom: 7,
  zoomControl: true,
  disableKineticPOan: false,
}

/**
 *
 * @summary Route 페이지에서 사용되는 NaverMap
 * @returns
 */
export default function NaverMap() {
  const map = useRef<naver.maps.Map>()
  useEffect(() => {
    map.current = new naver.maps.Map('map', options)
  }, [])

  return <div id="map" className="h-550 w-7/12 max-2xl:w-full"></div>
}
