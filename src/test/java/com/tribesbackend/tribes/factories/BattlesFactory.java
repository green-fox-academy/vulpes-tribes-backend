package com.tribesbackend.tribes.factories;

import com.tribesbackend.tribes.models.Battle;
import com.tribesbackend.tribes.models.Kingdom;
import com.tribesbackend.tribes.models.TribesUser;

import static com.tribesbackend.tribes.factories.KingdomFactory.createValidSampleKingdom;

public class BattlesFactory {
    KingdomFactory kingdomFactory = new KingdomFactory();
    TroopFactory troopFactory = new TroopFactory();

    public Battle createOneBattle(){
        Kingdom someOne = createValidSampleKingdom();
        Kingdom anotherOne = new Kingdom("bretagne", new TribesUser("Jonathan", "vivaLaBretagne"));
        return new Battle(someOne, anotherOne, TroopFactory.giveMeTroopList());
    }
}
