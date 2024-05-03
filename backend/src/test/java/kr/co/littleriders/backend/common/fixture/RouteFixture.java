package kr.co.littleriders.backend.common.fixture;


import kr.co.littleriders.backend.application.dto.request.RouteRequest;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.route.entity.Route;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)

@AllArgsConstructor
public enum RouteFixture {



    A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z;

    public Route toRoute(Academy academy){
        return Route.of(academy, this.name() +"코스");
    }

    public String getName(){
        return this.name() +"코스";
    }

    public RouteRequest toRouteRequest(){
        return new RouteRequest(this.name()+"코스");
    }




}
