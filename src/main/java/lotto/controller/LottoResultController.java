package lotto.controller;

import java.util.List;
import lotto.domain.GradingMachine;
import lotto.domain.Lotto;
import lotto.domain.LottoAnswer;
import lotto.domain.LottoGrade;
import lotto.domain.LottoResult;
import lotto.domain.RankingMachine;

public class LottoResultController {
    private LottoAnswer lottoAnswer;
    private List<Lotto> lottos;
    private GradingMachine gradingMachine;
    private RankingMachine rankingMachine = new RankingMachine();
    private LottoResult lottoResult = new LottoResult();
    private List<LottoGrade> lottoGrades;

    public LottoResultController(LottoAnswer lottoAnswer, List<Lotto> lottos) {
        this.lottoAnswer = lottoAnswer;
        this.lottos = lottos;
        this.gradingMachine = new GradingMachine(lottoAnswer, lottos);
    }

    public void createLottoGrades() {
        gradingMachine.run();
        lottoGrades = gradingMachine.getLottoGrades();
    }

    public void createLottoResult() {

    }


}
