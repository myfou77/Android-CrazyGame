package fr.crazygame.server;

public enum State {
	WAIT_PACKET, CLEAN_BUFFER, WAIT_LENGTH_NAME_GAME, WAIT_LENGTH_LANGUAGE, WAIT_LANGUAGE, WAIT_ADD_GAME, WAIT_NAME_GAME, WAIT_GAME, GAME_MORPION,
	WAIT_PLAYER_MORPION, GAME_MIXWORD, WAIT_PLAYER_MIXWORD, WAIT_SCORE, CHANGE_STATE, GAME_SHAKE, WAIT_SCORE_WORLD
}
