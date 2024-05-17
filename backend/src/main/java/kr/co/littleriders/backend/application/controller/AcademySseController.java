package kr.co.littleriders.backend.application.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import kr.co.littleriders.backend.application.dto.response.AcademyShuttleLandingInfoResponse;
import kr.co.littleriders.backend.application.dto.response.ShuttleLocationResponse;
import kr.co.littleriders.backend.application.facade.SseFacade;
import kr.co.littleriders.backend.global.auth.annotation.Auth;
import kr.co.littleriders.backend.global.auth.dto.AuthAcademy;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/academy/connection")
//@RequiredArgsConstructor
public class AcademySseController {

    private final SseFacade sseFacade;
    private final Map<String,SseEmitter> mockupSse = new ConcurrentHashMap();

    private final List<ShuttleLocationResponse> firstLocationList = new ArrayList<>();
    private final List<ShuttleLocationResponse> secondLocationList = new ArrayList<>();

    private int firstIndex = 0;
    private int secondIndex = 0;

    public AcademySseController(SseFacade sseFacade) {
        this.sseFacade = sseFacade;
        firstLocationList.add(ShuttleLocationResponse.of(1,37.47874,126.95183,3));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.47874,126.95186,6));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.47874,126.95189,8));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.47874,126.95194,12));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.47874,126.95198,13));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.47873,126.95203,14));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.47872,126.9521,18));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.47872,126.95216,19));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.47872,126.95222,19));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.47872,126.95228,18));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.47874,126.95234,18));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.47876,126.9524,17));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.47881,126.95243,16));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.4789,126.95248,21));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.47898,126.95248,23));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.47905,126.95249,26));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.47912,126.95251,28));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.4792,126.95253,30));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.47926,126.95254,29));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.47933,126.95254,28));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.4794,126.95255,28));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.47947,126.95256,28));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.47955,126.95257,29));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.47962,126.95257,29));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.47968,126.9526,28));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.47972,126.95259,26));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.47976,126.95258,24));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.47981,126.95257,23));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.47985,126.95257,20));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.47989,126.95257,18));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.4799,126.95258,15));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.47992,126.95258,12));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.47994,126.95257,11));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.47996,126.95256,11));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.47999,126.95255,11));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48002,126.95255,12));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48007,126.95256,16));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48014,126.95256,20));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48019,126.95257,21));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48024,126.95257,21));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48029,126.95257,19));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48035,126.95259,21));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.4804,126.95261,21));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48045,126.95263,20));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48048,126.95265,18));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48053,126.95266,18));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48055,126.95268,16));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48058,126.95269,14));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.4806,126.95269,11));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48061,126.9527,10));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48061,126.9527,8));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48061,126.9527,6));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48062,126.9527,5));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48063,126.95269,5));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48064,126.95269,4));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48066,126.95268,4));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48067,126.95268,3));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48067,126.95267,3));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48076,126.95267,3));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48078,126.95268,3));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.4808,126.95268,4));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48082,126.95268,5));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48084,126.95267,5));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48086,126.95265,6));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48088,126.95261,8));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48092,126.95258,9));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48098,126.95259,12));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48101,126.95259,12));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48107,126.95262,14));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48108,126.95262,12));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48115,126.95266,15));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.4812,126.95272,17));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48135,126.95279,28));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48144,126.95282,31));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48154,126.95288,33));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48164,126.95296,35));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48174,126.95303,35));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48183,126.9531,35));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48193,126.95318,36));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48201,126.95325,36));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48211,126.95333,38));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48221,126.95341,40));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48231,126.9535,41));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48242,126.95359,42));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48253,126.95368,43));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48265,126.95376,45));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48276,126.95385,46));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48287,126.95394,46));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48297,126.95403,47));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48308,126.95412,47));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48319,126.95421,48));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.4833,126.95431,48));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.4834,126.9544,49));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48351,126.9545,49));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48361,126.95458,49));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48372,126.95467,48));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48382,126.95475,48));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48393,126.95484,48));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48403,126.95492,47));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48413,126.955,47));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48423,126.95507,46));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48432,126.95514,43));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48442,126.9552,42));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.4845,126.95526,40));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48458,126.95532,38));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48464,126.95539,34));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48471,126.95545,31));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48474,126.95547,22));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48476,126.95549,16));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48477,126.95549,8));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48477,126.9555,4));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48487,126.95557,5));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48488,126.95559,7));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.4849,126.9556,9));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48492,126.95562,9));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48494,126.95563,8));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48496,126.95565,7));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48497,126.95566,7));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48499,126.95567,7));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.485,126.95568,6));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48501,126.95569,5));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48502,126.9557,5));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48503,126.95571,5));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48506,126.95574,4));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48508,126.95575,7));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48511,126.95578,11));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48515,126.95581,16));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48519,126.95584,17));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48524,126.95589,21));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48529,126.95593,22));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48534,126.95597,22));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48538,126.95601,22));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48543,126.95604,22));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48548,126.95607,22));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48553,126.95609,21));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48558,126.9561,20));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48562,126.95609,19));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48564,126.95606,15));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48567,126.95601,15));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48569,126.95596,16));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48571,126.9559,18));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48573,126.95584,20));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48575,126.95578,20));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48576,126.9557,24));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48577,126.95561,27));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48578,126.95553,27));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48579,126.95544,29));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.4858,126.95535,29));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48582,126.95526,30));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48583,126.95516,30));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48585,126.95506,31));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48586,126.95495,34));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48587,126.95485,33));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48589,126.95475,33));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48592,126.95465,32));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48594,126.95455,29));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48595,126.95447,25));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48595,126.95439,21));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48596,126.95434,17));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48597,126.9543,10));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48597,126.95425,3));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48597,126.95423,8));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48598,126.95419,12));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48598,126.95415,13));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48599,126.95409,17));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48601,126.95403,19));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48603,126.95397,20));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48604,126.95389,24));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48607,126.95382,25));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.4861,126.95375,26));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48613,126.95366,28));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48614,126.95356,32));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48616,126.95345,34));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48618,126.95333,36));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.4862,126.95322,37));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48623,126.9531,38));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48624,126.95298,38));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48626,126.95286,39));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48629,126.95275,38));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48632,126.95263,38));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48635,126.95252,37));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48637,126.95241,36));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48639,126.9523,36));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.4864,126.95219,34));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48642,126.95208,35));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48644,126.95197,34));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48646,126.95187,34));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48648,126.95175,36));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48647,126.95161,37));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48646,126.95147,39));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48647,126.95134,40));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48649,126.95121,41));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.4865,126.95107,42));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48651,126.95093,43));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48652,126.95079,43));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48653,126.95065,45));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48655,126.9505,46));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48657,126.95035,47));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48659,126.9502,48));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48661,126.95004,48));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48662,126.9499,46));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48664,126.94977,44));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48665,126.94964,41));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48665,126.94951,40));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48667,126.94941,36));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48669,126.94932,30));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.4867,126.94924,28));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.4867,126.94916,26));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.4867,126.94914,17));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.4867,126.94916,6));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48669,126.94912,4));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48669,126.94909,6));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.4867,126.94905,9));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.4867,126.949,12));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.4867,126.94895,14));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48671,126.94888,17));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48672,126.9488,20));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48672,126.94873,20));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48674,126.94865,22));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48674,126.94855,25));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48675,126.94844,28));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48677,126.94833,31));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48678,126.94821,32));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.4868,126.94808,35));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48681,126.94795,36));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48682,126.94783,37));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48684,126.94772,36));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48686,126.94761,35));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48686,126.94751,32));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48688,126.94742,30));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48688,126.94733,28));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48686,126.94724,26));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48685,126.94717,24));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48682,126.94711,20));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48681,126.94708,16));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48678,126.94707,11));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48674,126.94705,14));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48665,126.94705,21));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48659,126.94703,24));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48651,126.94703,28));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48643,126.94702,29));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48635,126.94702,30));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48628,126.94701,31));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.4862,126.947,30));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48613,126.94699,30));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48606,126.94698,29));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48599,126.94697,29));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48591,126.94696,30));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48584,126.94695,29));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48577,126.94694,29));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48569,126.94693,29));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48562,126.94692,29));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48555,126.9469,29));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48548,126.94689,28));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48542,126.94686,28));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48535,126.94684,28));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48528,126.94683,28));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.4852,126.94682,28));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48513,126.9468,28));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48506,126.94679,28));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48499,126.94678,29));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48492,126.94675,28));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48484,126.94673,28));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48477,126.94672,29));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.4847,126.9467,29));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48463,126.94668,30));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48455,126.94667,30));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48447,126.94665,30));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48439,126.94664,30));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48432,126.94662,30));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48424,126.94661,30));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48416,126.9466,30));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48409,126.94659,29));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48402,126.94658,28));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48394,126.94657,28));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48387,126.94656,27));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48381,126.94655,26));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48375,126.94653,24));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.4837,126.94653,22));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48365,126.94652,20));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48361,126.9465,19));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48358,126.94647,15));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48356,126.94645,13));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48355,126.94645,7));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48347,126.94652,4));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48344,126.94651,8));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.4834,126.94651,12));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48336,126.9465,14));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48333,126.94649,13));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48331,126.94649,9));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.4833,126.94649,6));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48328,126.94648,5));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48327,126.94648,4));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48326,126.94647,5));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48325,126.94647,4));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.4832,126.94643,3));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48319,126.94642,3));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48318,126.94642,4));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48316,126.94642,4));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48315,126.94643,5));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48313,126.94642,7));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48311,126.94643,6));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.4831,126.94642,6));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48307,126.94643,8));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48304,126.94642,12));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.483,126.94641,14));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48296,126.9464,18));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48291,126.94639,19));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48285,126.94638,20));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48281,126.94638,16));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48278,126.94638,14));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48275,126.94638,10));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48273,126.94638,8));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48271,126.94638,8));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48268,126.94637,9));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48265,126.94637,11));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48261,126.94636,14));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48257,126.94635,15));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48254,126.94634,15));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48249,126.94632,17));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48245,126.9463,18));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48239,126.94631,22));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48233,126.9463,22));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48228,126.9463,21));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48223,126.9463,20));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48218,126.94629,20));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48213,126.94629,19));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48207,126.9463,20));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48202,126.9463,19));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48196,126.94632,20));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48192,126.94634,19));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48191,126.9464,18));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48188,126.94645,18));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48186,126.9465,17));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48183,126.94658,19));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48181,126.94666,21));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48179,126.94675,24));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48177,126.94684,25));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48175,126.94694,27));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48174,126.94705,29));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48173,126.94716,30));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48174,126.94727,29));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48173,126.94736,27));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48173,126.94745,24));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48171,126.94752,20));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.4817,126.94757,15));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48169,126.9476,11));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48168,126.94762,7));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48167,126.94763,5));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48164,126.94763,6));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48159,126.94761,13));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48154,126.94762,16));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48149,126.94762,17));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48144,126.94763,18));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48139,126.94763,18));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48134,126.94763,17));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.4813,126.94763,16));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48128,126.94763,12));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48124,126.94762,13));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.4812,126.94762,13));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48116,126.9476,14));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48113,126.94759,15));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48108,126.94758,16));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48103,126.94758,19));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48098,126.94757,19));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48093,126.94755,19));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.4809,126.94753,17));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48085,126.94754,16));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48082,126.94753,14));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48079,126.94753,12));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48076,126.94753,11));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48074,126.94752,10));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48072,126.94751,10));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48067,126.94751,14));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48062,126.94749,16));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48057,126.94747,18));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48052,126.94746,19));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48047,126.94745,19));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48042,126.94742,20));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48037,126.94742,19));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48032,126.94739,21));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48025,126.94737,23));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48019,126.94737,22));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48014,126.94734,22));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48009,126.94733,21));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48003,126.94733,21));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.47997,126.94733,20));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.47995,126.94732,15));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.47992,126.94732,12));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.4799,126.94732,10));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.47987,126.94731,10));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.47986,126.9473,8));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.47985,126.94728,7));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.47983,126.94729,6));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.47982,126.94727,6));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.47982,126.94725,5));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.47982,126.94722,6));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.47982,126.9472,6));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.47981,126.94718,6));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.47981,126.94717,5));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.47981,126.94718,3));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.47981,126.94719,3));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.4798,126.94719,4));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.4798,126.9472,4));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.47874,126.95183,3));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.47874,126.95186,6));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.47874,126.95189,8));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.47874,126.95194,12));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.47874,126.95198,13));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.47873,126.95203,14));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.47872,126.9521,18));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.47872,126.95216,19));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.47872,126.95222,19));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.47872,126.95228,18));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.47874,126.95234,18));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.47876,126.9524,17));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.47881,126.95243,16));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.4789,126.95248,21));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.47898,126.95248,23));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.47905,126.95249,26));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.47912,126.95251,28));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.4792,126.95253,30));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.47926,126.95254,29));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.47933,126.95254,28));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.4794,126.95255,28));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.47947,126.95256,28));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.47955,126.95257,29));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.47962,126.95257,29));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.47968,126.9526,28));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.47972,126.95259,26));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.47976,126.95258,24));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.47981,126.95257,23));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.47985,126.95257,20));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.47989,126.95257,18));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.4799,126.95258,15));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.47992,126.95258,12));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.47994,126.95257,11));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.47996,126.95256,11));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.47999,126.95255,11));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48002,126.95255,12));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48007,126.95256,16));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48014,126.95256,20));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48019,126.95257,21));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48024,126.95257,21));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48029,126.95257,19));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48035,126.95259,21));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.4804,126.95261,21));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48045,126.95263,20));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48048,126.95265,18));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48053,126.95266,18));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48055,126.95268,16));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48058,126.95269,14));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.4806,126.95269,11));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48061,126.9527,10));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48061,126.9527,8));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48061,126.9527,6));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48062,126.9527,5));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48063,126.95269,5));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48064,126.95269,4));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48066,126.95268,4));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48067,126.95268,3));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48067,126.95267,3));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48076,126.95267,3));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48078,126.95268,3));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.4808,126.95268,4));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48082,126.95268,5));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48084,126.95267,5));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48086,126.95265,6));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48088,126.95261,8));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48092,126.95258,9));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48098,126.95259,12));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48101,126.95259,12));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48107,126.95262,14));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48108,126.95262,12));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48115,126.95266,15));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.4812,126.95272,17));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48135,126.95279,28));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48144,126.95282,31));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48154,126.95288,33));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48164,126.95296,35));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48174,126.95303,35));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48183,126.9531,35));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48193,126.95318,36));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48201,126.95325,36));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48211,126.95333,38));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48221,126.95341,40));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48231,126.9535,41));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48242,126.95359,42));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48253,126.95368,43));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48265,126.95376,45));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48276,126.95385,46));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48287,126.95394,46));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48297,126.95403,47));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48308,126.95412,47));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48319,126.95421,48));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.4833,126.95431,48));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.4834,126.9544,49));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48351,126.9545,49));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48361,126.95458,49));
        firstLocationList.add(ShuttleLocationResponse.of(1,37.48372,126.95467,48));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47934,126.95191,3));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47934,126.95191,3));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47893,126.9519,6));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47892,126.95193,10));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47891,126.95196,13));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47889,126.95201,16));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47888,126.95207,20));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47888,126.95213,20));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47889,126.9522,21));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47889,126.95227,23));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47891,126.95233,20));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47893,126.95238,16));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47895,126.9524,10));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47901,126.95241,18));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47905,126.95242,20));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47913,126.95244,24));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47919,126.95246,27));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47923,126.95248,24));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.4793,126.95249,27));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47937,126.95251,28));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47946,126.95251,30));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47954,126.95252,31));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47964,126.95253,32));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47973,126.95254,32));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47981,126.95255,32));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47989,126.95256,32));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47997,126.95256,31));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.48006,126.95256,31));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.48014,126.95256,30));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.48021,126.95255,29));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.48028,126.95255,29));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.48033,126.95255,28));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.48038,126.95256,27));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.48043,126.95256,25));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.48046,126.95257,23));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.4805,126.95257,22));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.48054,126.95258,21));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.48057,126.95258,19));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.48059,126.95259,18));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.48061,126.9526,17));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.48064,126.95261,16));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.48066,126.95262,15));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.48069,126.95265,15));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.4809,126.95279,15));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.4809,126.95285,15));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.48091,126.95287,13));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.4809,126.95288,12));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.48092,126.95291,12));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.4809,126.95292,11));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.4809,126.95292,10));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.48089,126.95293,10));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.4809,126.95293,9));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.4809,126.95292,9));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.48092,126.95293,9));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.48089,126.95295,8));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.48092,126.95295,9));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.48092,126.95296,8));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.48093,126.95296,8));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.48094,126.95296,7));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.48095,126.95297,7));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.48098,126.95301,9));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.48099,126.95303,9));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.48102,126.95306,12));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.48104,126.95312,15));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.48104,126.95319,19));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.48102,126.95327,22));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.48095,126.95335,26));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.48091,126.95344,28));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.48088,126.95353,29));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.48084,126.95364,34));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.48082,126.95376,37));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.4808,126.95388,38));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.48075,126.95397,37));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.48071,126.95407,36));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.48064,126.95413,34));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.4806,126.95421,33));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.48054,126.95426,31));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.48049,126.95433,30));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.48044,126.9544,30));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.48043,126.95446,26));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.48047,126.95464,12));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.48049,126.95471,13));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.48051,126.95476,12));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.48053,126.95479,9));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.48053,126.95482,7));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.48053,126.95483,6));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.48048,126.95479,5));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.48045,126.95477,4));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.48043,126.95477,4));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.4804,126.95476,4));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.48039,126.95476,4));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.48036,126.95475,4));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.48033,126.95474,3));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.48033,126.95494,3));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.48034,126.95499,8));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.48032,126.95503,11));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.48031,126.95508,15));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.48028,126.95514,18));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.48025,126.9552,21));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.48024,126.95528,22));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.48021,126.95535,24));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.48019,126.95545,26));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.48015,126.95554,29));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.48012,126.95563,29));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.48009,126.95574,32));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.48004,126.95584,35));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47999,126.95592,35));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47994,126.95602,36));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47988,126.95611,36));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47983,126.95621,36));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47978,126.95632,38));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47973,126.95643,39));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47968,126.95655,40));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47964,126.95668,41));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47958,126.95678,41));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47953,126.95689,40));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47949,126.95702,40));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47945,126.95714,39));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47939,126.95723,39));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47936,126.95737,40));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47932,126.9575,41));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47927,126.95762,40));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47923,126.95773,39));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47918,126.95783,37));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47914,126.95792,32));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47911,126.958,30));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47908,126.95808,29));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47905,126.95817,28));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47902,126.95826,28));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47899,126.95835,29));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47896,126.95845,31));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47892,126.95853,31));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47888,126.95863,33));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47885,126.95874,34));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.4788,126.95883,34));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47875,126.95892,35));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47869,126.959,35));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47861,126.95905,35));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47852,126.95909,35));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47844,126.95913,35));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47834,126.95915,34));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47824,126.95916,34));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47814,126.95916,33));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47805,126.95917,33));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.478,126.95926,33));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47795,126.95933,32));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.4779,126.95941,31));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47785,126.95948,31));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47779,126.95955,32));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47778,126.95969,33));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47771,126.95977,35));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47765,126.95985,36));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47759,126.95995,37));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47753,126.96003,37));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47758,126.96031,38));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47759,126.96053,39));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47758,126.9607,39));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47758,126.96089,40));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47756,126.96106,40));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47754,126.96121,40));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47752,126.96137,40));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47749,126.96152,40));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47746,126.96166,40));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47742,126.96179,39));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47739,126.96191,39));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47735,126.96204,39));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47732,126.96217,39));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47729,126.96233,41));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47726,126.96247,43));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.4772,126.96259,44));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47715,126.96273,45));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47712,126.96289,47));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47708,126.96305,47));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47704,126.96319,45));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.477,126.96334,45));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47696,126.96348,45));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.4769,126.96359,43));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47685,126.9637,42));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47681,126.96381,40));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47676,126.96392,38));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.4767,126.96399,36));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47668,126.9641,34));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47664,126.96418,30));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47662,126.96426,26));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47659,126.96433,24));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47657,126.96439,21));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47654,126.96442,16));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47652,126.96445,15));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47651,126.96445,9));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.4765,126.96445,3));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47649,126.96464,4));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47647,126.96467,9));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47646,126.96471,11));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47644,126.96477,15));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47642,126.96483,19));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47639,126.96489,21));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47637,126.96497,24));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47634,126.96505,26));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47633,126.96517,29));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.4763,126.96526,31));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47626,126.96536,33));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47624,126.96546,33));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.4762,126.96555,31));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47617,126.96563,28));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47614,126.9657,25));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47612,126.96576,21));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47609,126.96581,19));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47607,126.96586,17));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47605,126.96591,16));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47604,126.96596,18));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47602,126.96602,18));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.476,126.96607,17));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47597,126.96612,19));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47594,126.96619,22));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47592,126.96626,24));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47589,126.96635,28));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47585,126.96644,32));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47582,126.96657,37));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47578,126.96669,42));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47573,126.96682,45));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47568,126.96697,48));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47563,126.96711,49));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47558,126.96725,48));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47554,126.96739,47));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.4755,126.96753,47));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47546,126.96768,48));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47543,126.96783,49));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.4754,126.96799,51));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47538,126.96815,52));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47535,126.96831,53));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47533,126.96848,54));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47531,126.96865,54));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47529,126.96883,56));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47529,126.96902,57));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47529,126.96921,57));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47528,126.96939,56));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47528,126.96957,56));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47529,126.96975,56));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47529,126.96993,55));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.4753,126.9701,55));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47531,126.97027,54));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47534,126.97044,53));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47535,126.97061,53));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47537,126.97077,52));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47539,126.97093,50));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47543,126.97108,49));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47547,126.97123,48));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.4755,126.97137,46));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47553,126.9715,44));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47555,126.9716,39));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47557,126.97171,38));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.4756,126.97182,36));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47568,126.97193,38));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47572,126.97205,39));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47576,126.97217,40));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47578,126.97229,40));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47581,126.97242,42));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47584,126.97255,43));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47587,126.97269,44));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47589,126.97282,43));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47591,126.97293,40));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47592,126.97304,38));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47595,126.97316,38));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47597,126.97327,37));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.476,126.97338,37));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47602,126.97349,37));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47604,126.97361,38));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47608,126.97374,40));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47612,126.97388,42));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47616,126.97402,43));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47617,126.97416,44));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.4762,126.9743,45));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47623,126.97445,46));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47628,126.97461,48));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47632,126.97477,50));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47635,126.97493,51));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.4764,126.97511,53));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47643,126.97528,54));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47646,126.97545,54));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47649,126.97562,54));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47651,126.97578,53));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47652,126.97594,51));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47654,126.97609,50));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47656,126.97623,48));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47657,126.97635,42));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47658,126.97645,36));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47659,126.97655,33));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.4766,126.97664,31));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47661,126.97672,27));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47661,126.97678,22));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47662,126.97683,18));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47662,126.97687,15));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47663,126.9769,14));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47663,126.97694,13));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47664,126.97697,11));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47664,126.97701,11));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47665,126.97703,6));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47671,126.977,4));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47672,126.97703,8));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47672,126.97707,13));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47673,126.97713,17));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47674,126.9772,22));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47675,126.97728,27));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47676,126.97738,30));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47677,126.97749,34));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47677,126.9776,36));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47678,126.97772,38));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47679,126.97785,39));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.4768,126.97799,42));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47681,126.97813,44));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47682,126.97827,45));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47683,126.97842,46));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47684,126.97857,47));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47684,126.97872,48));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47684,126.97887,49));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47684,126.97903,50));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47684,126.97919,51));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47684,126.97936,52));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47684,126.97952,53));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47684,126.97969,53));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47683,126.97986,54));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47683,126.98003,53));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47681,126.9802,53));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47681,126.98036,52));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.4768,126.98053,52));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47678,126.9807,52));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47677,126.98086,52));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47676,126.98103,52));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47674,126.9812,53));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47672,126.98136,53));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.4767,126.98154,54));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47667,126.98171,56));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47665,126.98189,57));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47662,126.98207,58));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47659,126.98226,60));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47656,126.98246,62));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47653,126.98265,64));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.4765,126.98286,66));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47646,126.98306,67));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47643,126.98327,67));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.4764,126.98348,68));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47636,126.98369,68));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47633,126.9839,68));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.4763,126.98411,67));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47626,126.98431,66));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47623,126.98451,65));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.4762,126.98471,64));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47615,126.98488,60));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47611,126.98506,59));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47609,126.98524,56));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47606,126.98541,54));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47603,126.98557,52));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47599,126.98572,50));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47596,126.98586,48));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47591,126.98599,46));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47587,126.98612,46));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47583,126.98626,45));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47579,126.98638,44));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47576,126.98652,44));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47571,126.98664,43));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47567,126.98677,42));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47564,126.98689,42));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.4756,126.98702,41));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47557,126.98715,42));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47553,126.98728,42));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47549,126.98741,43));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47545,126.98754,45));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47541,126.98767,46));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47537,126.98782,48));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47532,126.98796,50));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47527,126.98811,51));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47523,126.98827,51));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47519,126.98842,52));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47514,126.98857,51));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.4751,126.98872,49));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47507,126.98887,48));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47503,126.98901,46));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47499,126.98914,45));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47496,126.98928,45));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47492,126.98941,45));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47488,126.98955,45));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47484,126.98969,47));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.4748,126.98983,48));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47476,126.98997,48));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47471,126.99011,47));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47468,126.99026,47));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47464,126.99039,45));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.4746,126.99052,45));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47456,126.99065,44));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47453,126.99078,44));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47449,126.99092,44));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47445,126.99105,44));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47442,126.99118,45));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47438,126.99132,46));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47434,126.99147,47));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.4743,126.99161,48));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47426,126.99176,50));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47421,126.99191,51));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47416,126.99207,52));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47411,126.99221,51));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47406,126.99236,52));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47402,126.99252,51));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47397,126.99268,51));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47394,126.99283,50));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.4739,126.99297,47));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47386,126.9931,46));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47383,126.99324,43));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47381,126.99336,39));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47378,126.99346,35));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47377,126.99356,32));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47375,126.99363,27));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47374,126.9937,22));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47373,126.99375,20));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47371,126.99378,15));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47367,126.99378,10));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47366,126.99379,6));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47381,126.99399,4));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47381,126.99401,7));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.4738,126.99405,11));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.4738,126.9941,15));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47379,126.99416,18));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47378,126.99423,21));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47376,126.9943,24));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47375,126.99438,27));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47373,126.99447,29));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47373,126.99457,31));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47372,126.99468,34));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47371,126.99479,37));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.4737,126.99491,39));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47368,126.99504,41));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47367,126.99517,42));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47365,126.9953,42));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47365,126.99544,44));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47364,126.99558,45));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47363,126.99572,45));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47362,126.99586,45));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47362,126.99601,45));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47361,126.99615,45));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47361,126.99629,45));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.4736,126.99643,45));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.4736,126.99657,44));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47361,126.99671,44));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47361,126.99685,42));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47362,126.99697,40));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47362,126.9971,39));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47363,126.99722,38));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47363,126.99734,38));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47364,126.99747,39));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47365,126.99759,40));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47366,126.99772,40));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47369,126.99785,41));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47371,126.99798,42));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47374,126.99811,42));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47376,126.99824,43));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.4738,126.99837,45));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47383,126.99851,46));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47387,126.99865,46));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47391,126.99879,46));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47394,126.99892,45));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47397,126.99905,45));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47401,126.99919,46));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47404,126.99933,46));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47408,126.99947,47));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47413,126.9996,47));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47417,126.99974,46));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47421,126.99987,46));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47425,127.0,44));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47428,127.00012,43));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47433,127.00024,43));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47438,127.00037,43));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47443,127.00049,43));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47449,127.00061,43));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47455,127.00074,45));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47461,127.00086,44));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47466,127.00097,43));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47469,127.00104,36));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47471,127.00108,28));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47467,127.0011,18));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47466,127.0011,11));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47466,127.00111,7));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47468,127.00109,3));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47472,127.0011,4));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47475,127.00114,8));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47478,127.00118,11));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47481,127.00123,14));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47483,127.00129,16));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47486,127.00136,19));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47489,127.00143,20));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47491,127.00149,21));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47493,127.00155,20));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47495,127.00161,20));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.475,127.00162,16));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47506,127.00161,15));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47511,127.00161,15));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47517,127.00161,18));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47523,127.00159,21));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47529,127.00157,21));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47536,127.00154,24));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47544,127.00149,28));
        secondLocationList.add(ShuttleLocationResponse.of(2,37.47551,127.00145,28));

    }


    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<SseEmitter> subscribe(@Auth AuthAcademy authAcademy) {

        SseEmitter sseEmitter = sseFacade.createAcademySseConnectionByAcademyId(authAcademy.getId());
        return ResponseEntity.ok(sseEmitter);
    }



    @GetMapping(value ="/mockup",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<SseEmitter> mocking() {
        SseEmitter sseEmitter = createSse();
        String uuid = UUID.randomUUID().toString();
        mockupSse.put(uuid, sseEmitter);
        return ResponseEntity.ok(sseEmitter);
    }

    @GetMapping("/shuttle/location/{shuttleId}")
    public ResponseEntity<Void> uploadMocking(@PathVariable(name = "shuttleId") long shuttleId){
        ShuttleLocationResponse shuttleLocationResponse;
        if(shuttleId == 1){
            shuttleLocationResponse = firstLocationList.get(firstIndex);
            firstIndex = (firstIndex +1) %firstLocationList.size();
        }
        else{
            shuttleLocationResponse =  secondLocationList.get(secondIndex);
            secondIndex = (secondIndex +1) % secondLocationList.size();
        }
        shuttleLocationResponse.setTime(LocalDateTime.now());
        SseEmitter.SseEventBuilder event = SseEmitter.event()
                //event 명 (event: event example)
                .name("location")
                //event id (id: id-1) - 재연결시 클라이언트에서 `Last-Event-ID` 헤더에 마지막 event id 를 설정
                .id(String.valueOf("location"))
                //event data payload (data: SSE connected)
                .data(shuttleLocationResponse)
                //SSE 연결이 끊어진 경우 재접속 하기까지 대기 시간 (retry: <RECONNECTION_TIMEOUT>)
                .reconnectTime(1000);

        mockupSse.forEach((id,sseEmitter) ->{
            try {
                sseEmitter.send(event);
            }catch (Exception ignored){}
        });
        return ResponseEntity.ok().build();
    }
    @GetMapping("/shuttle/rending-with-location-list/{shuttleId}")
    public ResponseEntity<Void> rendingWithLocationList(@PathVariable(name = "shuttleId") long shuttleId){
        int index = shuttleId == 1? firstIndex : secondIndex;
        List<ShuttleLocationResponse> base = shuttleId == 1 ? firstLocationList : secondLocationList;
        List<ShuttleLocationResponse> shuttleLocationResponseList = new ArrayList<>();
        for(int i = 0 ; i < index; i++){
            shuttleLocationResponseList.add(base.get(i));
        }

        AcademyShuttleLandingInfoResponse academyShuttleLandingInfoResponse = AcademyShuttleLandingInfoResponse.of(shuttleId, shuttleId, shuttleId, shuttleId, shuttleLocationResponseList, new ArrayList<>(), new ArrayList<>(),true);
        mockupSse.forEach((id,sseEmitter) ->{
            try {
                sseEmitter.send(SseEmitter.event()
                        //event 명 (event: event example)
                        .name("init")
                        //event id (id: id-1) - 재연결시 클라이언트에서 `Last-Event-ID` 헤더에 마지막 event id 를 설정
                        .id(String.valueOf("init"))
                        //event data payload (data: SSE connected)
                        .data(academyShuttleLandingInfoResponse)
                        //SSE 연결이 끊어진 경우 재접속 하기까지 대기 시간 (retry: <RECONNECTION_TIMEOUT>)
                        .reconnectTime(1000));
            }catch (Exception ignored){}
        });

        return ResponseEntity.ok().build();
    }


    @GetMapping("/shuttle/rending-with-location-list-and-board/{shuttleId}")
    public ResponseEntity<Void> rendingWithLocationListAndBoard(@PathVariable(name = "shuttleId") long shuttleId) throws JsonProcessingException {
        int index = shuttleId == 1? firstIndex : secondIndex;
        List<ShuttleLocationResponse> base = shuttleId == 1 ? firstLocationList : secondLocationList;
        List<ShuttleLocationResponse> shuttleLocationResponseList = new ArrayList<>();
        for(int i = 0 ; i < index; i++){
            shuttleLocationResponseList.add(base.get(i));
        }

        AcademyShuttleLandingInfoResponse academyShuttleLandingInfoResponse = AcademyShuttleLandingInfoResponse.of(shuttleId, shuttleId, shuttleId, shuttleId, shuttleLocationResponseList, new ArrayList<>(), new ArrayList<>(),true);

        List<Map<String,Object>> boardList = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = objectMapper.registerModule(new JavaTimeModule()).convertValue(academyShuttleLandingInfoResponse,Map.class);
        if(!shuttleLocationResponseList.isEmpty()){

            String child1 = "{\"child\":{\"academyChildId\":1,\"name\":\"김식이\",\"gender\":\"MALE\",\"imagePath\":\"kim.png\"}}";
            String child2 = "{\"child\":{\"academyChildId\":2,\"name\":\"두식이\",\"gender\":\"MALE\",\"imagePath\":\"park.png\"}}";
            String child3 = "{\"child\":{\"academyChildId\":3,\"name\":\"삼식이\",\"gender\":\"MALE\",\"imagePath\":\"choi.png\"}}";
            String child4 = "{\"child\":{\"academyChildId\":4,\"name\":\"사식이\",\"gender\":\"MALE\",\"imagePath\":\"go.png\"}}";
            String child5 = "{\"child\":{\"academyChildId\":5,\"name\":\"오식이\",\"gender\":\"MALE\",\"imagePath\":\"park.png\"}}";

            ShuttleLocationResponse temp = shuttleLocationResponseList.get(shuttleLocationResponseList.size()-1);

            String dateTimeString = temp.getTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS"));
            Map<String,Object> map1 = objectMapper.readValue(child1,Map.class);
            Map<String,Object> map2 = objectMapper.readValue(child2,Map.class);
            Map<String,Object> map3 = objectMapper.readValue(child3,Map.class);
            Map<String,Object> map4 = objectMapper.readValue(child4,Map.class);
            Map<String,Object> map5 = objectMapper.readValue(child5,Map.class);

            boardList.add(map1);
            boardList.add(map2);
            boardList.add(map3);
            boardList.add(map4);
            boardList.add(map5);
            for(Map<String,Object>  m : boardList){
                m.put("time",dateTimeString);
                m.put("latitude",temp.getLatitude());
                m.put("longitude",temp.getLongitude());
            }


        }
        map.put("boardList",boardList);






        mockupSse.forEach((id,sseEmitter) ->{
            try {
                sseEmitter.send(SseEmitter.event()
                        //event 명 (event: event example)
                        .name("init")
                        //event id (id: id-1) - 재연결시 클라이언트에서 `Last-Event-ID` 헤더에 마지막 event id 를 설정
                        .id(String.valueOf("init"))
                        //event data payload (data: SSE connected)
                        .data(map)
                        //SSE 연결이 끊어진 경우 재접속 하기까지 대기 시간 (retry: <RECONNECTION_TIMEOUT>)
                        .reconnectTime(1000));
            }catch (Exception ignored){}
        });




        return ResponseEntity.ok().build();
    }

    @GetMapping("/shuttle/rending-with-out-location-list/{shuttleId}")
    public ResponseEntity<Void> rendingWithOutShuttleLocation(@PathVariable(name = "shuttleId") long shuttleId){
        AcademyShuttleLandingInfoResponse academyShuttleLandingInfoResponse = AcademyShuttleLandingInfoResponse.of(shuttleId, shuttleId, shuttleId, shuttleId, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        mockupSse.forEach((id,sseEmitter) ->{
            try {
                sseEmitter.send(SseEmitter.event()
                        //event 명 (event: event example)
                        .name("init")
                        //event id (id: id-1) - 재연결시 클라이언트에서 `Last-Event-ID` 헤더에 마지막 event id 를 설정
                        .id(String.valueOf("init"))
                        //event data payload (data: SSE connected)
                        .data(academyShuttleLandingInfoResponse)
                        //SSE 연결이 끊어진 경우 재접속 하기까지 대기 시간 (retry: <RECONNECTION_TIMEOUT>)
                        .reconnectTime(1000));
            }catch (Exception ignored){}
        });
        return ResponseEntity.ok().build();
    }


    @GetMapping("/shuttle/board/{shuttleId}")
    public ResponseEntity<Void> boardMockup(@PathVariable(name = "shuttleId") long shuttleId) throws JsonProcessingException {

        int index = shuttleId == 1? firstIndex : secondIndex;
        List<ShuttleLocationResponse> base = shuttleId == 1 ? firstLocationList : secondLocationList;

        String child = "{\"child\":{\"academyChildId\":6,\"name\":\"육식이\",\"gender\":\"MALE\",\"imagePath\":\"park.png\"}}";
        ObjectMapper objectMapper = new ObjectMapper();
        ShuttleLocationResponse temp = base.get(index);

        String dateTimeString = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS"));
        Map<String,Object> map1 = objectMapper.readValue(child,Map.class);
        map1.put("time",dateTimeString);
        map1.put("latitude",temp.getLatitude());
        map1.put("longitude",temp.getLongitude());
        map1.put("shuttleId",temp.getShuttleId());


        mockupSse.forEach((id,sseEmitter) ->{
            try {
                sseEmitter.send(SseEmitter.event()
                        //event 명 (event: event example)
                        .name("board")
                        //event id (id: id-1) - 재연결시 클라이언트에서 `Last-Event-ID` 헤더에 마지막 event id 를 설정
                        .id(String.valueOf("board"))
                        //event data payload (data: SSE connected)
                        .data(map1)
                        //SSE 연결이 끊어진 경우 재접속 하기까지 대기 시간 (retry: <RECONNECTION_TIMEOUT>)
                        .reconnectTime(1000));
            }catch (Exception ignored){}
        });
        return ResponseEntity.ok().build();
    }




    private SseEmitter createSse() {
        SseEmitter emitter = new SseEmitter(300000000L);
        emitter.onTimeout(emitter::complete);
        emitter.onError(e -> {
            emitter.complete();
        });
        emitter.onCompletion(() -> {
        });
        return emitter;
    }

}
