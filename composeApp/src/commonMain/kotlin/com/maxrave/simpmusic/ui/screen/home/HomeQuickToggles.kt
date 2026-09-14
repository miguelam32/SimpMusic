package com.maxrave.simpmusic.ui.screen.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Memory
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.maxrave.domain.manager.DataStoreManager.Values.TRUE
import com.maxrave.simpmusic.viewModel.SettingsViewModel
import kotlinx.coroutines.flow.map

@Composable
fun HomeQuickToggles(viewModel: SettingsViewModel, modifier: Modifier = Modifier) {
    val lyricsUdpEnabled by remember {
        viewModel.lyricsUdpEnabled.map { it == TRUE }
    }.collectAsStateWithLifecycle(initialValue = true)

    val vuTcpEnabled by remember {
        viewModel.vuTcpEnabled.map { it == TRUE }
    }.collectAsStateWithLifecycle(initialValue = false)

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        IoToggleChip(
            active = false,
            highlightColor = Color(0xFFE05353),
            onClick = {
                viewModel.setLyricsUdpEnabled(false)
                viewModel.setVuTcpEnabled(false)
            },
        ) {
            Text("0", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }

        IoToggleChip(
            active = vuTcpEnabled,
            highlightColor = Color(0xFF8A5CF6),
            onClick = { viewModel.setVuTcpEnabled(!vuTcpEnabled) },
        ) {
            Icon(Icons.Default.Memory, contentDescription = "TCP audio")
        }

        IoToggleChip(
            active = lyricsUdpEnabled,
            highlightColor = Color(0xFF8A5CF6),
            onClick = { viewModel.setLyricsUdpEnabled(!lyricsUdpEnabled) },
        ) {
            Text(
                "X11",
                fontFamily = FontFamily.Cursive,
                fontStyle = FontStyle.Italic,
                fontSize = 15.sp,
            )
        }
    }
}

@Composable
private fun IoToggleChip(
    active: Boolean,
    highlightColor: Color,
    onClick: () -> Unit,
    content: @Composable () -> Unit,
) {
    Surface(
        shape = RoundedCornerShape(50),
        color = if (active) highlightColor.copy(alpha = 0.25f) else Color(0x33FFFFFF),
        border = BorderStroke(1.dp, if (active) highlightColor else Color(0x55FFFFFF)),
        modifier = Modifier
            .height(36.dp)
            .clickable(onClick = onClick),
    ) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.padding(horizontal = 16.dp)) {
            content()
        }
    }
}
