import { useRef, useState } from 'react'

import StationList from '@pages/StationPage/StationList'

import MapOverlay from './MapOverlay'

import Page from '@layouts/Page'

export default function StationPage() {
  const [isOverlayOpen, setOverlayOpen] = useState<boolean>(false)

  const mapDiv = useRef<HTMLDivElement>(null)
  const mapRef = useRef<naver.maps.Map>(null)
  const handleAddButton = () => {
    setOverlayOpen((prev: boolean) => !prev)
  }

  const handleCloseButton = () => {
    setOverlayOpen(false)
  }

  return (
    <Page>
      <div className="flex h-[80vh] w-full">
        <div className="flex h-full w-9/12 justify-center" ref={mapDiv}>
          <img
            className="absolute z-10 mb-8 self-center transition-all ease-in-out hover:mb-12 hover:size-14"
            src="/src/assets/image/map-center-icon.svg"
            width={40}
            onClick={handleAddButton}
          />
          {isOverlayOpen && (
            <MapOverlay mapRef={mapRef} handleCloseButton={handleCloseButton} />
          )}
        </div>
        <StationList
          mapDiv={mapDiv}
          mapRef={mapRef}
          handleAddButton={handleAddButton}
        />
      </div>
    </Page>
  )
}
