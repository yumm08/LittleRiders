import { useRef } from 'react'

import Page from '@layouts/Page'

export default function StationPage() {
  const mapDiv = useRef<HTMLDivElement>(null)

  return (
    <Page>
      <div className="flex w-full">
        <div className="w-9/12" ref={mapDiv}>
          1234356
        </div>
        <div className="w-3/12">asdfasdf</div>
      </div>
    </Page>
  )
}
