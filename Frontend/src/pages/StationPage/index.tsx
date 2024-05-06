import { useRef } from 'react'

import StationList from '@pages/StationPage/StationList'

import MapOverlay from './MapOverlay'

import Page from '@layouts/Page'

export default function StationPage() {
  const mapDiv = useRef<HTMLDivElement>(null)
  return (
    <Page>
      <div className="flex h-[80vh] w-full">
        <div className="flex h-full w-9/12 justify-center" ref={mapDiv}>
          <img
            className="absolute z-10 self-center"
            src="/src/assets/image/map-center-icon.svg"
            width={40}
          />
          <MapOverlay />
        </div>
        <StationList mapDiv={mapDiv} />
      </div>
    </Page>
  )
}
